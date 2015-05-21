package pyp_metodo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import lab_controlador.INSERTAR_GRUPO;

import sun.misc.BASE64Decoder;
//import yol_prb_bean.prueba;
//import adm_bean.Pyp;
import adm_logica.Conexion;

/*public class MetodoFormPyp {

	public ResultSet BuscarPaciente(String tp, String numdoc){
		ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement(); 
			rs = st.executeQuery("select pac_codigo_paciente from adm_paciente where tipo_documento='"+tp+"'  AND numero_documento='"+numdoc+"' ");
			System.out.println("select * from adm_paciente where tipo_documento='"+tp+"'  AND numero_documento='"+numdoc+"' ");
			
		}catch(Exception ex){
			ex.getMessage();
		}
		System.out.println();
	
		return rs;
	}

*/


/////////////////////////////////////////

public class MetodoFormPyp {
	public ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento) {
		ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "apac.nombre,"
					+ "apac.primer_apellido,"
					+ "apac.segundo_apellido,"
					+ "apac.pac_codigo_paciente,"
					+ "apac.genero,"
					+ "apac.tipo_documento,"
					+ "apac.numero_documento,"
					+ "(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento,5))AS 'Edad',"
					+ "ent.nombre_entidad"
					+
					// ",aad.adm_numero_ingreso," +
					// "aad.fecha_registro," +
					// "aad.hora_registro," +
					// "aad.estado "+
					" FROM "
					+ "adm_paciente apac,"
					+ "adm_convenio con,"
					+ "adm_entidad ent"
					+
					// "," +
					// " adm_admisiones aad "+
					" WHERE "
					+ "apac.tipo_documento='"
					+ TipoDocumento
					+ "' "
					+ "AND apac.numero_documento='"
					+ NumeroDocumento
					+ "' "
					+ "AND apac.conv_numero_contrato_fk = con.conv_numero_contrato "
					+ "AND con.ent_nit_contratante_fk = ent.ent_nit"
			// +" AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk"+
			// " ORDER BY aad.adm_numero_ingreso DESC "+
			// "LIMIT 1"
			);
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}
	//////////////////////////////////
	 public java.sql.ResultSet BuscarGenero(String TipoDoc,String NumDocumento){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT genero FROM adm_paciente WHERE tipo_documento='"+TipoDoc+"' AND numero_documento='"+NumDocumento+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAsignarCita>>BuscarDatosPacienteCE "+ex);
	        }	
	        return rs;
	    }	
	//////////////////////////////////
	 
	 
	 					/*OBTENER HORA Y FECHA PARA DETERMINAR EL CODIGO DEL INFORME*/
	//Obtener Hora y Fecha
		public java.sql.ResultSet ObtenerCodigoInforme(String Fecha, String Hora) {
			
			java.sql.ResultSet rs = null;
			Statement st = null;
			
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_informe_diagnostico WHERE fecha_diagnostico='"+Fecha+"' AND hora_diagnostico='"+Hora+"' ");

			} catch (Exception ex) {
				ex.getMessage();
			}

			return rs;
		}
					/* FIN DE OBTENER HORA Y FECHA PARA DETERMINAR EL CODIGO DEL INFORME*/
	 
	//Método insertarFormato
		 public void insertarFormato(String horaRealizacion, String fechaRealizacion, String usuario, String codPaciente){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_formato_planificacion_familiar(hora_realizacion, fecha_realizacion, usuario, pac_codigo_paciente_fk) VALUES(?,?,?,?)");				
					ps.setString(1, horaRealizacion);
					ps.setString(2, fechaRealizacion);
					ps.setString(3, usuario);
					ps.setString(4, codPaciente);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarFormato "+ex);
				}
						
			}
		 
		  
	//Método insertarEstudio
	 public void insertarEstudio(String nivEstudio, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_nivel_estudio(nivel_estudio, id_informe_diagnostico_pyp_fk) VALUES(?,?)");				
				ps.setString(1, nivEstudio);
				ps.setString(2, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoPyp>>insertarEstudio "+ex);
			}
					
		}
	 

	//Modificar Estudio
		 public void actualizarEstudio(String idInforme, String nivEstudio){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_nivel_estudio" +
							" SET " +
							"nivel_estudio = '"+nivEstudio+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");				
					psc.executeUpdate();
					psc.close();
					con.cerrar();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
 
	//Método insertarConvivencia 
	 public void insertarConvivencia(String chk_Convivencia, String numHijos, String numNietos, String numPadres, String numOtros, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_convivientes(estado_convivencia, hijos, nietos, padres, otros, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?)");				
				ps.setString(1, chk_Convivencia);
				ps.setString(2, numHijos);
				ps.setString(3, numNietos);
				ps.setString(4, numPadres);
				ps.setString(5, numOtros);
				ps.setString(6, codReporte);				
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoPyp>>insertarConvivencia "+ex);
			}
					
		}
	 
	 
		//Modificar Convivencia
		 public void actualizarConvivencia(String idInforme, String numHijos, String numNietos, String numPadres, String numOtros, String chk_Convivencia){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_convivientes" +
							" SET " +
							"hijos = '"+numHijos+"'," +
							"nietos = '"+numNietos+"'," +
							"padres = '"+numPadres+"'," +
							"otros = '"+numOtros+"'," +
							"estado_convivencia = '"+chk_Convivencia+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.execute();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	 
	 
	 //Método insertarActividadLaboral
	 public void insertarActividadLaboral(String actLaboral, String campoFuma, String campoAlcohol, String campoDrogas, String actFisica, String codReporte){	 

			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_actividad_laboral(actividad_laboral, fuma, alcohol, drogas, actividad_fisica, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?)");				
				ps.setString(1, actLaboral);
				ps.setString(2, campoFuma);
				ps.setString(3, campoAlcohol);
				ps.setString(4, campoDrogas);
				ps.setString(5, actFisica);
				ps.setString(6, codReporte);
				
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoPyp>>insertarActividadLaboral "+ex);
			}
					
		}
	 
	 
		//Modificar ActividadLaboral
		 public void actualizarActividadLaboral(String idInforme, String actLaboral, String campoFuma, String campoAlcohol, String campoDrogas, String actFisica){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_actividad_laboral" +
							" SET " +
							"fuma = '"+campoFuma+"'," +
							"alcohol = '"+campoAlcohol+"'," +
							"drogas = '"+campoDrogas+"'," +
							"actividad_fisica = '"+actFisica+"'," +
							"actividad_laboral = '"+actLaboral+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.execute();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	 
	 
	 //Método insertarViviendaHabitat
	 public void insertarViviendaHabitat(String vivPropiedad, String radHabitacionCompartida, String radAnimalesDomesticos, String radGas, String radLuz, String radAseo, String radAlcantarillado, String radAgua, String radRiesgoPareja, String radMaltratoActual, String radActosViolencia, String radSatisfaccion, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_vivienda_habitat(Propiedad, habitacion_compartida, animales_domesticos, gas, luz, aseo, alcantarillado, agua, riesgo_pareja, maltrato_actual, actos_violencia, satisfaccion, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, vivPropiedad);
				ps.setString(2, radHabitacionCompartida);
				ps.setString(3, radAnimalesDomesticos);
				ps.setString(4, radGas);
				ps.setString(5, radLuz);
				ps.setString(6, radAseo);
				ps.setString(7, radAlcantarillado);
				ps.setString(8, radAgua);
				ps.setString(9, radRiesgoPareja);
				ps.setString(10, radMaltratoActual);
				ps.setString(11, radActosViolencia);
				ps.setString(12, radSatisfaccion);
				ps.setString(13, codReporte);
				
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoPyp>>insertarViviendaHabitat "+ex);
			}
					
		}
	 
	 
	//Modificar ViviendaHabitat
	 public void actualizarViviendaHabitat(String idInforme, String vivPropiedad, String radGas, String radLuz, String radAseo, String radHabitacionCompartida, String radAlcantarillado, String radAnimalesDomesticos, String radAgua, String radRiesgoPareja, String radMaltratoActual, String radActosViolencia, String radSatisfaccion){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE " +
						"pyp_vivienda_habitat" +
						" SET " +
						"Propiedad = '"+vivPropiedad+"'," +
						"habitacion_compartida = '"+radHabitacionCompartida+"'," +
						"animales_domesticos = '"+radAnimalesDomesticos+"'," +
						"gas = '"+radGas+"'," +
						"luz = '"+radLuz+"'," +
						"aseo = '"+radAseo+"'," +
						"alcantarillado = '"+radAlcantarillado+"'," +
						"agua = '"+radAgua+"'," +
						"riesgo_pareja = '"+radRiesgoPareja+"'," +
						"maltrato_actual = '"+radMaltratoActual+"'," +
						"actos_violencia = '"+radActosViolencia+"'," +
						"satisfaccion = '"+radSatisfaccion+"'" +
						" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
				psc.execute();
				psc.close();
				con.cerrar();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	 
	
	//Método insertarAntecedentesFamiliares
		 public void insertarAntecedentesFamiliares(String radFamHipertensionArterial, String radFamHepatopatias, String radFamDiabetes, String radFamTumores, String radFamCardiopatias, String radFamMentales, String codReporte){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_antecedentes_familiares(fam_hipertension_arterial, fam_hepatopatias, fam_diabetes, fam_tumores, fam_cardiopatias, fam_mentales, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?)");				
					ps.setString(1, radFamHipertensionArterial);
					ps.setString(2, radFamHepatopatias);
					ps.setString(3, radFamDiabetes);
					ps.setString(4, radFamTumores);
					ps.setString(5, radFamCardiopatias);
					ps.setString(6, radFamMentales);
					ps.setString(7, codReporte);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarAntecedentesFamiliares "+ex);
				}
						
			}
		 
		//Modificar AntecedentesFamiliares
		 public void actualizarAntecedentesFamiliares(String idInforme, String radFamHipertensionArterial, String radFamDiabetes, String radFamCardiopatias, String radFamHepatopatias, String radFamTumores, String radFamMentales){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_antecedentes_familiares" +
							" SET " +
							"fam_hipertension_arterial = '"+radFamHipertensionArterial+"'," +
							"fam_diabetes = '"+radFamDiabetes+"'," +
							"fam_cardiopatias = '"+radFamCardiopatias+"'," +
							"fam_hepatopatias = '"+radFamHepatopatias+"'," +
							"fam_tumores = '"+radFamTumores+"'," +
							"fam_mentales = '"+radFamMentales+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}


		//Método insertarAntecedentesPersonales
		 public void insertarAntecedentesPersonales(String radPerHipertension, String radPerMentales, String radPerDiabetes, String radPerInfeccionesPelvicas, String radPerCardiopatias, String radPerInfeccionCervical, String radPerHepatopatias, String radPerFlujoVaginal, String radPerNefritis, String radPerCirugiaGinecologica, String radPerTumores, String radPerCitologiasPrevias, String radPerTromboFlebitis, String radPerHabitoFumar, String codReporte){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_antecedentes_personales(per_hipertension, per_mentales, per_diabetes, per_infecciones_pelvicas, per_cardiopatias, per_infeccion_cervical, per_hepatopatias, per_flujo_vaginal, per_nefritis, per_cirugia_ginecologica, per_tumores, per_citologias_previas, per_trombo_flebitis, per_habito_fumar, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
					ps.setString(1, radPerHipertension);
					ps.setString(2, radPerMentales);
					ps.setString(3, radPerDiabetes);
					ps.setString(4, radPerInfeccionesPelvicas);
					ps.setString(5, radPerCardiopatias);
					ps.setString(6, radPerInfeccionCervical);
					ps.setString(7, radPerHepatopatias);
					ps.setString(8, radPerFlujoVaginal);
					ps.setString(9, radPerNefritis);
					ps.setString(10, radPerCirugiaGinecologica);
					ps.setString(11, radPerTumores);
					ps.setString(12, radPerCitologiasPrevias);
					ps.setString(13, radPerTromboFlebitis);
					ps.setString(14, radPerHabitoFumar);
					ps.setString(15, codReporte);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarAntecedentesPersonales "+ex);
				}
						
			}
		 
		//Modificar AntecedentesPersonales
		 public void actualizarAntecedentesPersonales(String idInforme, String radPerHipertension, String radPerDiabetes, String radPerCardiopatias, String radPerHepatopatias, String radPerNefritis, String radPerTumores, String radPerTromboFlebitis, String radPerHabitoFumar, String radPerMentales, String radPerInfeccionesPelvicas, String radPerInfeccionCervical, String radPerFlujoVaginal, String radPerCirugiaGinecologica, String radPerCitologiasPrevias){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_antecedentes_personales" +
							" SET " +
							"per_hipertension = '"+radPerHipertension+"'," +
							"per_diabetes = '"+radPerDiabetes+"'," +
							"per_cardiopatias = '"+radPerCardiopatias+"'," +
							"per_hepatopatias = '"+radPerHepatopatias+"'," +
							"per_nefritis = '"+radPerNefritis+"'," +
							"per_tumores = '"+radPerTumores+"'," +
							"per_trombo_flebitis = '"+radPerTromboFlebitis+"'," +
							"per_habito_fumar = '"+radPerHabitoFumar+"'," +
							"per_mentales = '"+radPerMentales+"'," +
							"per_infecciones_pelvicas = '"+radPerInfeccionesPelvicas+"'," +
							"per_infeccion_cervical = '"+radPerInfeccionCervical+"'," +
							"per_flujo_vaginal = '"+radPerFlujoVaginal+"'," +
							"per_cirugia_ginecologica = '"+radPerCirugiaGinecologica+"'," +
							"per_citologias_previas = '"+radPerCitologiasPrevias+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 				/*CONSULTA PARA SABER SI EL PACIENTE TIENE ANTECEDENTES DE CODIGO CIE 10*/
		//Obtener Hora y Fecha
			public java.sql.ResultSet ObtenerAntecedentesCodigoCieFamiliares(String CodPaciente, String CodCIE) {
				
				java.sql.ResultSet rs = null;
				Statement st = null;
				
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery("SELECT * FROM hic_antfamiliar WHERE codpac_fk='"+CodPaciente+"' AND codigoCIE_fk='"+CodCIE+"'");

				} catch (Exception ex) {
					ex.getMessage();
				}

				return rs;
			}
			
			//Obtener Hora y Fecha
			public java.sql.ResultSet ObtenerAntecedentesCodigoCiePersonales(String CodPaciente, String CodCIE, String ObsAntPer) {
				
				java.sql.ResultSet rs = null;
				Statement st = null;
				
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery("SELECT * FROM adm_diagnosticocola WHERE cedpac_fk='"+CodPaciente+"' AND codigoCIE_fk='"+CodCIE+"' AND observacion='"+ObsAntPer+"'");

				} catch (Exception ex) {
					ex.getMessage();
				}

				return rs;
			}
			
			
	public java.sql.ResultSet LlenoNivelEstudio(String CodReporte) {
		java.sql.ResultSet rs = null;
		Statement st = null;		
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM pyp_nivel_estudio WHERE id_informe_diagnostico_pyp_fk='"+CodReporte+"' ");
			

		} catch (Exception ex) {
			ex.getMessage();
		}		
		return rs;
	}
	
	public java.sql.ResultSet LlenoAntecedentesFamiliares(String CodReporte) {
		
		java.sql.ResultSet rs = null;
		Statement st = null;		
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM pyp_antecedentes_familiares WHERE id_informe_diagnostico_pyp_fk='"+CodReporte+"' ");

		} catch (Exception ex) {
			ex.getMessage();
		}		
		return rs;
	}
	
	public java.sql.ResultSet LlenoControlesEnfermeria(String CodReporte) {
		
		java.sql.ResultSet rs = null;
		Statement st = null;		
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM pyp_controles_enfermeria WHERE id_informe_diagnostico_pyp_fk='"+CodReporte+"' ");

		} catch (Exception ex) {
			ex.getMessage();
		}		
		return rs;
	}

	public java.sql.ResultSet LlenoConductas(String CodReporte) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;		
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM pyp_conductas WHERE id_informe_diagnostico_pyp_fk='"+CodReporte+"' ");

	} catch (Exception ex) {
		ex.getMessage();
	}		
	return rs;
	}

	public java.sql.ResultSet LlenoIndicaciones(String CodReporte) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;		
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM pyp_observaciones_especiales WHERE id_informe_diagnostico_pyp_fk='"+CodReporte+"' ");

	} catch (Exception ex) {
		ex.getMessage();
	}		
	return rs;
	}
	
	
		 
		 						/*METODOS ONCHANGE CODIGOS CIE 10*/
		 //Método CieSelectHipArtFam
		 public void CieSelectHipArtFam(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String numDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, numDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectHipArtFam "+ex);
				}
						
			}
		 
		//Método CieSelectHepFam
		 public void CieSelectHepFam(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String numDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, numDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectHepFam "+ex);
				}
						
			}
		 
		//Método CieSelectDiaFam
		 public void CieSelectDiaFam(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String numDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, numDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectDiaFam "+ex);
				}
						
			}
		 
		//Método CieSelectTumFam
		 public void CieSelectTumFam(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String numDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, numDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectTumFam "+ex);
				}
						
			}
		 
		//Método CieSelectCarFam
		 public void CieSelectCarFam(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String numDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, numDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectCarFam "+ex);
				}
						
			}
		 
		//Método CieSelectMenFam
		 public void CieSelectMenFam(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String numDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, numDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectMenFam "+ex);
				}
						
			}
		 
		 													
		 													/*PERSONALES*/
		//Método CieSelectHipArtPer
		 public void CieSelectHipArtPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectHipArtPer "+ex);
				}
						
			}
		 
		//Método CieSelectMenPer
		 public void CieSelectMenPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectMenPer "+ex);
				}
						
			}
		 
		//Método CieSelectDiaPer
		 public void CieSelectDiaPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectDiaPer "+ex);
				}
						
			}
		 
		//Método CieSelectInfPelPer
		 public void CieSelectInfPelPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectInfPelPer "+ex);
				}
						
			}
		 
		//Método CieSelectCarPer
		 public void CieSelectCarPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectCarPer "+ex);
				}
						
			}
		 
		//Método CieSelectInfCerPer
		 public void CieSelectInfCerPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectInfCerPer "+ex);
				}
						
			}
		 
		//Método CieSelectHepPer
		 public void CieSelectHepPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectHepPer "+ex);
				}
						
			}
		 
		//Método CieSelectFluVagPer
		 public void CieSelectFluVagPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectFluVagPer "+ex);
				}
						
			}
		 
		//Método CieSelectNefPer
		 public void CieSelectNefPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectNefPer "+ex);
				}
						
			}
		 
		//Método CieSelectTumPer
		 public void CieSelectTumPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectTumPer "+ex);
				}
						
			}
		 
		//Método CieSelectTroFlePer
		 public void CieSelectTroFlePer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>CieSelectTroFlePer "+ex);
				}
						
			}
		 					/*FIN METODOS ONCHANGE CODIGOS CIE 10*/
		 
		 
		//Método insertarSelectHipArt
		 public void insertarSelectHipArt(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String NumeroDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, NumeroDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectHipArt "+ex);
				}
						
			}
		 
		//Modificar actualizarSelectHipArt
		 public void actualizarSelectHipArt(String IdPaciente, String CodigoCIE, String CodUsuario, String fecha, String hora, String Observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"adm_diagnosticocola" +
							" SET " +
							"codigoCIE_fk = '"+CodigoCIE+"'," +
							"codigoUsuario_fk = '"+CodUsuario+"'," +
							"fecha = '"+fecha+"'," +
							"hora = '"+hora+"'" +
							" WHERE cedpac_fk ='"+IdPaciente+"'" +
							"AND observacion ='"+Observaciones+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		//Método insertarSelectHep
		 public void insertarSelectHep(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String NumeroDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, NumeroDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectHep "+ex);
				}
						
			}
		 
		//Modificar actualizarSelectHep
		 public void actualizarSelectHep(String IdPaciente, String CodigoCIE, String CodUsuario, String fecha, String hora, String Observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"adm_diagnosticocola" +
							" SET " +
							"codigoCIE_fk = '"+CodigoCIE+"'," +
							"codigoUsuario_fk = '"+CodUsuario+"'," +
							"fecha = '"+fecha+"'," +
							"hora = '"+hora+"'" +
							" WHERE cedpac_fk ='"+IdPaciente+"'" +
							"AND observacion = '"+Observaciones+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		//Método insertarSelectDia
		 public void insertarSelectDia(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String NumeroDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, NumeroDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectDia "+ex);
				}
						
			}
		 
		//Modificar actualizarSelectDia
		 public void actualizarSelectDia(String IdPaciente, String CodigoCIE, String CodUsuario, String fecha, String hora, String Observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"adm_diagnosticocola" +
							" SET " +
							"codigoCIE_fk = '"+CodigoCIE+"'," +
							"codigoUsuario_fk = '"+CodUsuario+"'," +
							"fecha = '"+fecha+"'," +
							"hora = '"+hora+"'" +
							" WHERE cedpac_fk ='"+IdPaciente+"'" +
							"AND observacion = '"+Observaciones+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		//Método insertarSelectTum
		 public void insertarSelectTum(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String NumeroDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, NumeroDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectTum "+ex);
				}
						
			}
		 
		//Modificar actualizarSelectTum
		 public void actualizarSelectTum(String IdPaciente, String CodigoCIE, String CodUsuario, String fecha, String hora, String Observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"adm_diagnosticocola" +
							" SET " +
							"codigoCIE_fk = '"+CodigoCIE+"'," +
							"codigoUsuario_fk = '"+CodUsuario+"'," +
							"fecha = '"+fecha+"'," +
							"hora = '"+hora+"'" +
							" WHERE cedpac_fk ='"+IdPaciente+"'" +
							"AND observacion = '"+Observaciones+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		//Método insertarSelectCar
		 public void insertarSelectCar(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String NumeroDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, NumeroDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectCar "+ex);
				}
						
			}
		 
		//Modificar actualizarSelectCar
		 public void actualizarSelectCar(String IdPaciente, String CodigoCIE, String CodUsuario, String fecha, String hora, String Observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"adm_diagnosticocola" +
							" SET " +
							"codigoCIE_fk = '"+CodigoCIE+"'," +
							"codigoUsuario_fk = '"+CodUsuario+"'," +
							"fecha = '"+fecha+"'," +
							"hora = '"+hora+"'" +
							" WHERE cedpac_fk ='"+IdPaciente+"'" +
							"AND observacion = '"+Observaciones+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		//Método insertarSelectMen
		 public void insertarSelectMen(String Familiar, String Observaciones, String CodigoCIE, String CodPaciente, String codUsuario, String hora, String fecha, String NumeroDocumento){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO hic_antfamiliar(familiar, observacion, codigoCIE_fk, codpac_fk, codusu_fk, hora, fecha, cedpac_fk) VALUES(?,?,?,?,?,?,?,?)");				
					ps.setString(1, Familiar);
					ps.setString(2, Observaciones);
					ps.setString(3, CodigoCIE);
					ps.setString(4, CodPaciente);
					ps.setString(5, codUsuario);
					ps.setString(6, hora);
					ps.setString(7, fecha);
					ps.setString(8, NumeroDocumento);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectMen "+ex);
				}
						
			}
		 
		//Modificar actualizarSelectMen
		 public void actualizarSelectMen(String IdPaciente, String CodigoCIE, String CodUsuario, String fecha, String hora, String Observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"adm_diagnosticocola" +
							" SET " +
							"codigoCIE_fk = '"+CodigoCIE+"'," +
							"codigoUsuario_fk = '"+CodUsuario+"'," +
							"fecha = '"+fecha+"'," +
							"hora = '"+hora+"'" +
							" WHERE cedpac_fk ='"+IdPaciente+"'" +
							"AND observacion = '"+Observaciones+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		 ////**PERSONALES
		//Método insertarSelectHipArtPer
		 public void insertarSelectHipArtPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectHipArtPer "+ex);
				}
						
			}

		 
		//Método insertarSelectMenPer
		 public void insertarSelectMenPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectMenPer "+ex);
				}
						
			}

		 
		//Método insertarSelectDiaPer
		 public void insertarSelectDiaPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectDiaPer "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectCarPer
		 public void insertarSelectCarPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectCarPer "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectHepPer
		 public void insertarSelectHepPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectHepPer "+ex);
				}
						
			}
		 
		
		//Método insertarSelectNef
		 public void insertarSelectNef(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectNef "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectTumPer
		 public void insertarSelectTumPer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectTumPer "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectTroFle
		 public void insertarSelectTroFle(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectTroFle "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectInfPel
		 public void insertarSelectInfPel(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectInfPel "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectInfCer
		 public void insertarSelectInfCer(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectInfCer "+ex);
				}
						
			}
		 
		 
		//Método insertarSelectFluVag
		 public void insertarSelectFluVag(String CedPaciente, String CodigoCIE, String codUsuario, String fecha, String hora, String Observaciones){	 
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO adm_diagnosticocola(cedpac_fk, codigoCIE_fk, codigoUsuario_fk, fecha, hora, observacion) VALUES(?,?,?,?,?,?)");				
					ps.setString(1, CedPaciente);
					ps.setString(2, CodigoCIE);
					ps.setString(3, codUsuario);
					ps.setString(4, fecha);
					ps.setString(5, hora);
					ps.setString(6, Observaciones);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarSelectFluVag "+ex);
				}
						
			}
 
		 
		//Método insertarHistoriasEmbarazosAnteriores
		 public void insertarHistoriasEmbarazosAnteriores(String EmbarazoAntes, String terminaEmbarazo, String mesesGestacion, String tipoParto, String abortos, String otrosAntecedentes, String codReporte){
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_historias_embarazos_anteriores(embarazo_antes, terminacion_embarazo, meses_gestacion, tipo_parto, aborto, otros_antecedentes, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?)");				
				    ps.setString(1, EmbarazoAntes);
				    ps.setString(2, terminaEmbarazo);
					ps.setString(3, mesesGestacion);
					ps.setString(4, tipoParto);
					ps.setString(5, abortos);
					ps.setString(6, otrosAntecedentes);
					ps.setString(7, codReporte);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarHistoriasEmbarazosAnteriores "+ex);
				}
						
			}
		 
		 
		//Modificar HistoriasEmbarazosAnteriores
		 public void actualizarHistoriasEmbarazosAnteriores(String idInforme, String terminaEmbarazo, String mesesGestacion, String tipoParto, String abortos, String otrosAntecedentes){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_historias_embarazos_anteriores" +
							" SET " +
							"terminacion_embarazo = '"+terminaEmbarazo+"'," +
							"meses_gestacion = '"+mesesGestacion+"'," +
							"tipo_parto = '"+tipoParto+"'," +
							"aborto = '"+abortos+"'," +
							"otros_antecedentes = '"+otrosAntecedentes+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 	 
		//Método insertarHistoriasMenstruales
		 public void insertarHistoriasMenstruales(String radCiclosRegulares, String ultimaMenstruacion, String tipoTrasMenstruales, String codReporte){
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_historias_menstruales(ciclos_regulares, ultima_menstruacion, trastornos_menstruales, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?)");				
				    ps.setString(1, radCiclosRegulares);
					ps.setString(2, ultimaMenstruacion);
					ps.setString(3, tipoTrasMenstruales);
					ps.setString(4, codReporte);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarHistoriasMenstruales "+ex);
				}
						
			}
		 
		 
		//Modificar HistoriasMenstruales
		 public void actualizarHistoriasMenstruales(String idInforme, String radCiclosRegulares, String ultimaMenstruacion, String tipoTrasMenstruales){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_historias_menstruales" +
							" SET " +
							"ciclos_regulares = '"+radCiclosRegulares+"'," +
							"ultima_menstruacion = '"+ultimaMenstruacion+"'," +
							"trastornos_menstruales = '"+tipoTrasMenstruales+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		  

		//Método insertarHistoriaConceptual
		 public void insertarHistoriaConceptual(String radMetodoAnticonceptivo, String metodoUtilizado, String tiempoUtilizacionDesde, String tiempoUtilizacionHasta, String selpreescrito, String preescrito, String problemas, String codReporte){
				
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_historias_conceptuales(metodos_antes_consulta, metodos_utilizados, tiempo_utilizacion_desde, tiempo_utilizacion_hasta, seleccion_preescrito, preescrito, problemas, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?)");				
				    ps.setString(1, radMetodoAnticonceptivo);
					ps.setString(2, metodoUtilizado);
					ps.setString(3, tiempoUtilizacionDesde);
					ps.setString(4, tiempoUtilizacionHasta);
					ps.setString(5, selpreescrito);
					ps.setString(6, preescrito);
					ps.setString(7, problemas);
					ps.setString(8, codReporte);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarHistoriaConceptual "+ex);
				}
						
			}


		//Modificar HistoriaConceptual
		 public void actualizarHistoriaConceptual(String idInforme, String radMetodoAnticonceptivo, String metodoUtilizado, String tiempoUtilizacionDesde, String tiempoUtilizacionHasta, String selpreescrito, String preescrito, String problemas){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_historias_conceptuales" +
							" SET " +
							"metodos_antes_consulta = '"+radMetodoAnticonceptivo+"'," +
							"metodos_utilizados = '"+metodoUtilizado+"'," +
							"tiempo_utilizacion_desde = '"+tiempoUtilizacionDesde+"'," +
							"tiempo_utilizacion_hasta = '"+tiempoUtilizacionHasta+"'," +
							"seleccion_preescrito = '"+selpreescrito+"'," +
							"preescrito = '"+preescrito+"'," +
							"problemas = '"+problemas+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 	 
		 //Método insertarPlanificacionFamiliar
		 public void insertarPlanificacionFamiliar(String fecVisita, String fecUltMenstruacion, String edadMujer, String pariedad, String socEconomico, String intEmbarazo, String radAborto, String radMortinato, String radCesarea, String radPrematuro, String patActual, String codReporte){	 
					 
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_planificacion_familiar(fecha_visita, fecha_ult_menstruacion, edad_mujer, pariedad, socio_economico, intervalo_embarazo, aborto, cesarea, mortinato, prematuro, patologia_actual, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");				
				    ps.setString(1, fecVisita);
				    ps.setString(2, fecUltMenstruacion);
				    ps.setString(3, edadMujer);
					ps.setString(4, pariedad);
					ps.setString(5, socEconomico);
					ps.setString(6, intEmbarazo);
					ps.setString(7, radAborto);
					ps.setString(8, radMortinato);
					ps.setString(9, radCesarea);
					ps.setString(10, radPrematuro);
					ps.setString(11, patActual);
					ps.setString(12, codReporte);

					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarPlanificacionFamiliar "+ex);
				}
						
			}
		 
		//Modificar PlanificacionFamiliar
		 public void actualizarPlanificacionFamiliar(String idInforme, String fecVisita, String fecUltMenstruacion, String edadMujer, String pariedad, String socEconomico, String intEmbarazo, String radAborto, String radMortinato, String radCesarea, String radPrematuro, String patActual){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_planificacion_familiar" +
							" SET " +
							"fecha_visita = '"+fecVisita+"'," +
							"fecha_ult_menstruacion = '"+fecUltMenstruacion+"'," +
							"edad_mujer = '"+edadMujer+"'," +
							"pariedad = '"+pariedad+"'," +
							"socio_economico = '"+socEconomico+"'," +
							"intervalo_embarazo = '"+intEmbarazo+"'," +
							"aborto = '"+radAborto+"'," +
							"cesarea = '"+radCesarea+"'," +
							"mortinato = '"+radMortinato+"'," +
							"prematuro = '"+radPrematuro+"'," +
							"patologia_actual = '"+patActual+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 	 
		 
		//Método insertarRiesgoReproductivo
		 public void insertarRiesgoReproductivo(String riesgoReproductivo, String codReporte){	 
					 
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_riesgo_reproductivo(riesgo_reproductivo, id_informe_diagnostico_pyp_fk) VALUES(?,?)");				
				    ps.setString(1, riesgoReproductivo);
				    ps.setString(2, codReporte);

					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarRiesgoReproductivo "+ex);
				}
						
			}
		 
		//Modificar RiesgoReproductivo
		 public void actualizarRiesgoReproductivo(String idInforme, String riesgoReproductivo){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_riesgo_reproductivo" +
							" SET " +
							"riesgo_reproductivo = '"+riesgoReproductivo+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		 //Método insertarExamenFisico
		 public void insertarExamenFisico(String radMamasNormales, String radUteAneNormales, String radSigEmbarazo, String radMiemInfVarices, String radCerNormal, String radEdemas, String peso, String tensionArterial, String numeroSemanas, String otrosHallazgos, String observaciones, String codReporte) {
			
			 try {
				PreparedStatement ps = null;
				Conexion con=new Conexion();																						
				ps=con.conn.prepareStatement("INSERT INTO pyp_examen_fisico(mamas_normales, utero_anexos_normales, edemas, signos_embarazo, varices, cervis_normal, peso, tension_arterial, num_semanas, otros_hallazgos, observaciones, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, radMamasNormales);
				ps.setString(2, radUteAneNormales);
				ps.setString(3, radSigEmbarazo);
				ps.setString(4, radMiemInfVarices);
				ps.setString(5, radCerNormal);
				ps.setString(6, radEdemas);
				ps.setString(7, peso);
				ps.setString(8, tensionArterial);
				ps.setString(9, numeroSemanas);
				ps.setString(10, otrosHallazgos);
				ps.setString(11, observaciones);
				ps.setString(12, codReporte);
				
				ps.execute();
				ps.close();
				con.cerrar();
				
			} catch (Exception ex) {
				ex.getMessage();
				System.out.print("Error en MetodoPyp>>insertarExamenFisico "+ex);
			}
		}
		 
		//Modificar ExamenFisico
		 public void actualizarExamenFisico(String idInforme, String peso, String tensionArterial, String numeroSemanas, String radMamasNormales, String radSigEmbarazo, String radCerNormal, String radUteAneNormales, String radMiemInfVarices, String radEdemas, String otrosHallazgos, String observaciones){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_examen_fisico" +
							" SET " +
							"peso = '"+peso+"'," +
							"tension_arterial = '"+tensionArterial+"'," +
							"num_semanas = '"+numeroSemanas+"'," +
							"mamas_normales = '"+radMamasNormales+"'," +
							"signos_embarazo = '"+radSigEmbarazo+"'," +
							"cervis_normal = '"+radCerNormal+"'," +
							"utero_anexos_normales = '"+radUteAneNormales+"'," +
							"varices = '"+radMiemInfVarices+"'," +
							"edemas = '"+radEdemas+"'," +
							"otros_hallazgos = '"+otrosHallazgos+"'," +
							"observaciones = '"+observaciones+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		 //Método insertarExamenesPracticados
		 public void insertarExamenesPracticados(String radPruEmbarazo, String radCitCerVaginal, String radFroVaginal, String otrosExaPracticados, String codReporte) {
			
			 try {
				PreparedStatement ps = null;
				Conexion con=new Conexion();
																										
				ps=con.conn.prepareStatement("INSERT INTO pyp_examenes_practicados(prueba_embarazo, citologia_cervico_vaginal, frotis_vaginal, otros, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?)");
				ps.setString(1, radPruEmbarazo);
				ps.setString(2, radCitCerVaginal);
				ps.setString(3, radFroVaginal);
				ps.setString(4, otrosExaPracticados);
				ps.setString(5, codReporte);
				
				ps.execute();
				ps.close();
				con.cerrar();
				
			} catch (Exception ex) {
				ex.getMessage();
				System.out.print("Error en MetodoPyp>>insertarExamenesPracticados "+ex);
			}
		}
		 
		//Modificar ExamenesPracticados
		 public void actualizarExamenesPracticados(String idInforme, String radPruEmbarazo, String radCitCerVaginal, String radFroVaginal, String otrosExaPracticados){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_examenes_practicados" +
							" SET " +
							"prueba_embarazo = '"+radPruEmbarazo+"'," +
							"citologia_cervico_vaginal = '"+radCitCerVaginal+"'," +
							"frotis_vaginal = '"+radFroVaginal+"'," +
							"otros = '"+otrosExaPracticados+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		//Método insertarMetodoReproductivo
		 public void insertarMetodoReproductivo(String metPildoras, String usoPildoras, String cualPildoras, String fechaPildoras, String metDIU, String usoDIU, String cualDIU, String fechaDIU, String metInyectables, String usoInyectables, String cualInyectables, String fechaInyectables, String metOtros, String usoOtros, String cualOtros, String fechaOtros, String codReporte){	 
			
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_metodo_reproductivo(metodo_pildoras, uso_pildoras, cuales_pildoras, fecha_pildoras, metodo_diu, uso_diu, cuales_diu, fecha_diu, metodo_inyectables, uso_inyectables, cuales_inyectables, fecha_inyectables, metodo_otros, uso_otros, cuales_otros, fecha_otros, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				    ps.setString(1, metPildoras);
				    ps.setString(2, usoPildoras);
				    ps.setString(3, cualPildoras);
					ps.setString(4, fechaPildoras);
					ps.setString(5, metDIU);
					ps.setString(6, usoDIU);
					ps.setString(7, cualDIU);
					ps.setString(8, fechaDIU);
					ps.setString(9, metInyectables);
					ps.setString(10, usoInyectables);
					ps.setString(11, cualInyectables);
					ps.setString(12, fechaInyectables);
					ps.setString(13, metOtros);
					ps.setString(14, usoOtros);
					ps.setString(15, cualOtros);
					ps.setString(16, fechaOtros);
					ps.setString(17, codReporte);

					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarMetodoReproductivo "+ex);
				}
						
			}
		 
		//Modificar MetodoReproductivo
		 public void actualizarMetodoReproductivo(String idInforme, String metPildoras, String usoPildoras, String cualPildoras, String fechaPildoras, String metDIU, String usoDIU, String cualDIU, String fechaDIU, String metInyectables, String usoInyectables, String cualInyectables, String fechaInyectables, String metOtros, String usoOtros, String cualOtros, String fechaOtros){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_metodo_reproductivo" +
							" SET " +
							"metodo_pildoras = '"+metPildoras+"'," +
							"uso_pildoras = '"+usoPildoras+"'," +
							"cuales_pildoras = '"+cualPildoras+"'," +
							"fecha_pildoras = '"+fechaPildoras+"'," +
							"metodo_diu = '"+metDIU+"'," +
							"uso_diu = '"+usoDIU+"'," +
							"cuales_diu = '"+cualDIU+"'," +
							"fecha_diu = '"+fechaDIU+"'," +
							"metodo_inyectables = '"+metInyectables+"'," +
							"uso_inyectables = '"+usoInyectables+"'," +
							"cuales_inyectables = '"+cualInyectables+"'," +
							"fecha_inyectables = '"+fechaInyectables+"'," +
							"metodo_otros = '"+metOtros+"'," +
							"uso_otros = '"+usoOtros+"'," +
							"cuales_otros = '"+cualOtros+"'," +
							"fecha_otros = '"+fechaOtros+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		 //Método insertarControlesEnfermeria
		 public void insertarControlesEnfermeria(String fecha, String peso, String tensionArterial, String satisfaccionMetodo, String trastornosMenstruales, String cambiosComportamiento, String cefaleas, String mareos, String manchasPiel, String molestiasMamas, String edemas, String varices, String expulsionDispositivo, String dolorBajoVientre, String flujoVaginal, String codReporte){	 
					 
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_controles_enfermeria(fecha, peso, tension_arterial, satisfaccion_metodo, trastornos_menstruales, cambios_comportamiento, cefaleas, mareos, manchas_piel, molestias_mamas, edemas, varices, expulsion_dispositivo, dolor_bajo_vientre, flujo_vaginal, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				    ps.setString(1, fecha);
				    ps.setString(2, peso);
				    ps.setString(3, tensionArterial);
					ps.setString(4, satisfaccionMetodo);
					ps.setString(5, trastornosMenstruales);
					ps.setString(6, cambiosComportamiento);
					ps.setString(7, cefaleas);
					ps.setString(8, mareos);
					ps.setString(9, manchasPiel);
					ps.setString(10, molestiasMamas);
					ps.setString(11, edemas);
					ps.setString(12, varices);
					ps.setString(13, expulsionDispositivo);
					ps.setString(14, dolorBajoVientre);
					ps.setString(15, flujoVaginal);
					ps.setString(16, codReporte);

					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarControlesEnfermeria "+ex);
				}
						
			}
		 
		//Modificar ControlesEnfermeria
		 public void actualizarControlesEnfermeria(String idInforme, String fecha, String peso, String tensionArterial, String satisfaccionMetodo, String trastornosMenstruales, String cambiosComportamiento, String cefaleas, String mareos, String manchasPiel, String molestiasMamas, String edemas, String varices, String expulsionDispositivo, String dolorBajoVientre, String flujoVaginal){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_controles_enfermeria" +
							" SET " +
							"fecha = '"+fecha+"'," +
							"peso = '"+peso+"'," +
							"tension_arterial = '"+tensionArterial+"'," +
							"satisfaccion_metodo = '"+satisfaccionMetodo+"'," +
							"trastornos_menstruales = '"+trastornosMenstruales+"'," +
							"cambios_comportamiento = '"+cambiosComportamiento+"'," +
							"cefaleas = '"+cefaleas+"'," +
							"mareos = '"+mareos+"'," +
							"manchas_piel = '"+manchasPiel+"'," +
							"molestias_mamas = '"+molestiasMamas+"'," +
							"edemas = '"+edemas+"'," +
							"varices = '"+varices+"'," +
							"expulsion_dispositivo = '"+expulsionDispositivo+"'," +
							"dolor_bajo_vientre = '"+dolorBajoVientre+"'," +
							"flujo_vaginal = '"+flujoVaginal+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		//Método insertarConductas
		 public void insertarConductas(String estiloVidaSaludable, String nutricionAlimentacion, String prevencionEnfermedadesTransmisionSexual, String consejeriaMetodosPlanificacionFamiliar, String signosSintomas, String PrevencionCancerCuelloUterino, String autoexamenMama, String lactanciaMaterna, String prevencionDiabetesHipertensionOsteoporosis, String abusoSexual, String violenciaIntrafamiliar, String consecuenciasAlcoholCigarrillos, String complicacionesDrogas, String autoestima, String cuidadosRecienNacido, String derechosReproductivos, String saludSexualReproductiva, String otroProgramaPYP, String valoracionPsicologia, String valoracionNutricion, String valoracionGinecologia, String valoracionUrologia, String consultaMedica, String otraEspecialidad, String codReporte){	 
					 
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_conductas(estilo_vida_saludable, nutricion_alimentacion, prev_enfer_transmision_sexual, consejeria_metodos_plan_fam, signos_sintomas, prev_cancer_cuello_uter, autoexamen_mama, lactancia_materna, prev_diab_hiper_osteop, abuso_sexual, violencia_intrafamiliar, consec_alcohol_cigarrillos, complicaciones_drogas, autoestima, cuidados_recien_nacido, derechos_reproductivos, salud_sexual_reproductiva, otro_prog_pyp, valoracion_psicologia, valoracion_nutricion, valoracion_ginecologia, valoracion_urologia, consulta_medica, otra_especialidad, id_informe_diagnostico_pyp_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				    ps.setString(1, estiloVidaSaludable);
				    ps.setString(2, nutricionAlimentacion);
				    ps.setString(3, prevencionEnfermedadesTransmisionSexual);
					ps.setString(4, consejeriaMetodosPlanificacionFamiliar);
					ps.setString(5, signosSintomas);
					ps.setString(6, PrevencionCancerCuelloUterino);
					ps.setString(7, autoexamenMama);
					ps.setString(8, lactanciaMaterna);
					ps.setString(9, prevencionDiabetesHipertensionOsteoporosis);
					ps.setString(10, abusoSexual);
					ps.setString(11, violenciaIntrafamiliar);
					ps.setString(12, consecuenciasAlcoholCigarrillos);
					ps.setString(13, complicacionesDrogas);
					ps.setString(14, autoestima);
					ps.setString(15, cuidadosRecienNacido);
					ps.setString(16, derechosReproductivos);
					ps.setString(17, saludSexualReproductiva);
					ps.setString(18, otroProgramaPYP);
					ps.setString(19, valoracionPsicologia);
					ps.setString(20, valoracionNutricion);
					ps.setString(21, valoracionGinecologia);
					ps.setString(22, valoracionUrologia);
					ps.setString(23, consultaMedica);
					ps.setString(24, otraEspecialidad);
					ps.setString(25, codReporte);
					
					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarConductas "+ex);
				}
						
			}
		 
		//Modificar Conductas
		 public void actualizarConductas(String idInforme, String estiloVidaSaludable, String nutricionAlimentacion, String prevencionEnfermedadesTransmisionSexual, String consejeriaMetodosPlanificacionFamiliar, String signosSintomas, String PrevencionCancerCuelloUterino, String autoexamenMama, String lactanciaMaterna, String prevencionDiabetesHipertensionOsteoporosis, String abusoSexual, String violenciaIntrafamiliar, String consecuenciasAlcoholCigarrillos, String complicacionesDrogas, String autoestima, String cuidadosRecienNacido, String derechosReproductivos, String saludSexualReproductiva, String otroProgramaPYP, String valoracionPsicologia, String valoracionNutricion, String valoracionGinecologia, String valoracionUrologia, String consultaMedica, String otraEspecialidad){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_conductas" +
							" SET " +
							"estilo_vida_saludable = '"+estiloVidaSaludable+"'," +
							"nutricion_alimentacion = '"+nutricionAlimentacion+"'," +
							"prev_enfer_transmision_sexual = '"+prevencionEnfermedadesTransmisionSexual+"'," +
							"consejeria_metodos_plan_fam = '"+consejeriaMetodosPlanificacionFamiliar+"'," +
							"signos_sintomas = '"+signosSintomas+"'," +
							"prev_cancer_cuello_uter = '"+PrevencionCancerCuelloUterino+"'," +
							"autoexamen_mama = '"+autoexamenMama+"'," +
							"lactancia_materna = '"+lactanciaMaterna+"'," +
							"prev_diab_hiper_osteop = '"+prevencionDiabetesHipertensionOsteoporosis+"'," +
							"abuso_sexual = '"+abusoSexual+"'," +
							"violencia_intrafamiliar = '"+violenciaIntrafamiliar+"'," +
							"consec_alcohol_cigarrillos = '"+consecuenciasAlcoholCigarrillos+"'," +
							"complicaciones_drogas = '"+complicacionesDrogas+"'," +
							"autoestima = '"+autoestima+"'," +
							"cuidados_recien_nacido = '"+cuidadosRecienNacido+"'," +
							"derechos_reproductivos = '"+derechosReproductivos+"'," +
							"salud_sexual_reproductiva = '"+saludSexualReproductiva+"'," +
							"otro_prog_pyp = '"+otroProgramaPYP+"'," +
							"valoracion_psicologia = '"+valoracionPsicologia+"'," +
							"valoracion_nutricion = '"+valoracionNutricion+"'," +
							"valoracion_ginecologia = '"+valoracionGinecologia+"'," +
							"valoracion_urologia = '"+valoracionUrologia+"'," +
							"consulta_medica = '"+consultaMedica+"'," +
							"otra_especialidad = '"+otraEspecialidad+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		 
		 //Método insertarObservacionesEspeciales
		 public void insertarObservacionesEspeciales(String observacionesEspeciales, String codReporte){	 
					 
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_observaciones_especiales(observaciones_especiales, id_informe_diagnostico_pyp_fk) VALUES(?,?)");				
				    ps.setString(1, observacionesEspeciales);
				    ps.setString(2, codReporte);

					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarObservacionesEspeciales "+ex);
				}
						
			}
		 
		//Modificar ObservacionesEspeciales
		 public void actualizarObservacionesEspeciales(String idInforme, String ObservacionesEspeciales){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_observaciones_especiales" +
							" SET " +
							"observaciones_especiales = '"+ObservacionesEspeciales+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		 
		 
		 //Método insertarEvolucionClinica
		 public void insertarEvolucionClinica(String evolucionClinica, String codReporte){	 
					 
				try{				
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_evolucion_clinica(evolucion_clinica, id_informe_diagnostico_pyp_fk) VALUES(?,?)");				
				    ps.setString(1, evolucionClinica);
				    ps.setString(2, codReporte);

					ps.execute();
					ps.close();
					con.cerrar();
							
				}catch(Exception ex){
					ex.getMessage();
					System.out.print("Error en MetodoPyp>>insertarEvolucionClinica "+ex);
				}
						
			}
		 
		//Modificar EvolucionClinica
		 public void actualizarEvolucionClinica(String idInforme, String evolucionClinica){
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE " +
							"pyp_evolucion_clinica" +
							" SET " +
							"evolucion_clinica = '"+evolucionClinica+"'" +
							" WHERE id_informe_diagnostico_pyp_fk ='"+idInforme+"'");
					psc.executeUpdate();
					psc.close();
					con.cerrar();
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		 
		    /*********************************************************/
			/********************   INFORME  *************************/
			/*********************************************************/
		 
		 //Insertar Informe
		 public boolean insertarEncabezadoInformePyp(
					String nombreUsuario,
					String fecha, 
					String hora, 
					String codPaciente
					) {

				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement("INSERT INTO " +
							"pyp_informe_diagnostico(" +
							"id_usuario," +
							"fecha_diagnostico," +
							"hora_diagnostico," +
							"id_paciente" +
							")" +
							" VALUES " +
							"(?,?,?,?)");
					
					psc.setString(1, nombreUsuario);
					psc.setString(2, fecha);
					psc.setString(3, hora);
					psc.setString(4, codPaciente);

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
		 
		 
		 	public java.sql.ResultSet ObtenerGenero (String TipoDocumento, String NumeroDocumento) {
				
		 		java.sql.ResultSet rs = null;
		 		Statement st = null;
		 		
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery("SELECT genero  FROM adm_paciente WHERE tipo_documento='"+TipoDocumento+"' AND numero_documento='"+NumeroDocumento+"'");
					
				} catch (Exception e) {
					System.out.println("Error en MetodoAsignarCita>>BuscarObtenerGenero "+e);
					e.getMessage();
					// TODO: handle exception
				}
				return rs;
			}
		 
		 
		 ///Buscar Informe
		 public java.sql.ResultSet buscarInformesPYPRealizados(String TipoDocumento, String NumeroDocumento) {
				java.sql.ResultSet rs = null;
				Statement st = null;
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery(
							"SELECT " +
					        "CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido),"+
							"inf.id_informe_diagnostico_pyp," +
							"CONCAT(inf.fecha_diagnostico,'/',inf.hora_diagnostico), " +
							"pac_codigo_paciente "+
							"FROM " +
							"adm_paciente pac," +
							"pyp_informe_diagnostico inf " +
							"WHERE " +
							"pac.tipo_documento ='" +TipoDocumento+"' "+
							"AND pac.numero_documento='" +NumeroDocumento+"' "+
							"AND pac.pac_codigo_paciente = inf.id_paciente"); 					
					
				} catch (Exception ex) {
					System.out.println("Error en MetodoPyp>>buscarInformesPYPRealizados "+ex);
				}
				return rs;
			}
		 
		 
		 //CE
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
		        	System.out.println("Error en MetodoAsignarCita>>BuscarDatosPacienteCE "+ex);
		        }	
		        return rs;
		    }
		 
		 
		 //Codigo Actual Informe PYP Planificacion Familiar
		 public int codigoActualinformePyp() {
				java.sql.ResultSet rs = null;
				Statement st = null;
				int valor = 1;
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery("SELECT MAX(id_informe_diagnostico_pyp)FROM pyp_informe_diagnostico");
					rs.next();
					if (rs.getString(1) != null) {
						valor = Integer.parseInt(rs.getString(1));
					}
				} catch (Exception ex) {
					ex.getMessage();
				}

				return valor;
			}
		 
		 
}
	
