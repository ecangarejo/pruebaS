/**
 * logica: MetodoVerFormatos se encuentran las inserciones,consultas y actualizaciones
 * para visualizar lso formatos, con sus areas y estas con las preguntas que se le asignaron.
*/
package hic_metodo;

import hic_bean.CrearFormatoHic;
import hic_bean.CrearPacLab;
import hic_bean.Formulacion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import adm_logica.Conexion;


public class MetodoVerFormatos {
	
	//signos vitales##############
	
	//buscar hic_desocultar_sv  todos lo desocultar por admision y fecha kardex
	
		public Vector<String> buscarTodosDesocultar(
				String fecha,
				String adm_paciente
				) {
			
			java.sql.ResultSet rs = null;
			Statement st = null;
			Vector <String> miVector = null;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT nom_desocultar FROM "+
						"hic_desocultar_sv "+
						"WHERE "+
						"adm_pac_fk='"+adm_paciente+"' "+
						"AND fecha_kardex='"+fecha+"' "); 
				
				
				 miVector = new Vector<String>();
					
					while(rs.next()) {

						miVector.add(rs.getString(1));
						
					}
				
				
			} catch (Exception ex) {
				System.out.println("Error en MetodoVerFormatos>>BuscarTodosDesocultar "	+ ex);
			}
			System.out.println("vector desocultar sv "+miVector);
			return miVector;
			
			
			
		}
		
		//fin buscar hic_desocultar_sv todos lo desocultar por admision y fecha kardex

	//metodo para guardar hic_desocultar_sv
	
		public boolean insertarDesocultarSv(
				String nom_desocultar,
				String cod_paciente,
				String cod_usuario, 
				String fecha,
				String hora, 
				String adm_paciente,
				String fecha_kardex,
				String estado
				
				) {

			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO hic_desocultar_sv"+
			           "(nom_desocultar," +
			           "cod_pac_fk," +
			           "usuario_fk," +
			           "fecha," +
			           "hora," +
			           "adm_pac_fk," +
			           "fecha_kardex," +
			           "estado "+
			           ")" +
			           " VALUES(?,?,?,?,?,?,?,?)");
				psc.setString(1, nom_desocultar);
				psc.setString(2, cod_paciente);
				psc.setString(3, cod_usuario);
				psc.setString(4, fecha);
				psc.setString(5, hora);
				psc.setString(6, adm_paciente);
				psc.setString(7, fecha_kardex);
				psc.setString(8, estado);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Error en MetodoVerFormatos>>InsertarDesocultarSv "	+ ex);
				return false;
			}
		}
		
		//fin metodo para guardar hic_desocultar_sv
		
		//buscar hic_desocultar_sv por nombre
		
		public java.sql.ResultSet buscarDesocultarSv(
				String desocultar,
				String fecha,
				String adm_paciente
				) {
			
			java.sql.ResultSet rs = null;
			Statement st = null;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT * FROM "+
						"hic_desocultar_sv "+
						"WHERE "+
						"nom_desocultar = '"+desocultar+"' "+
						"AND adm_pac_fk='"+adm_paciente+"' "+
						"AND fecha_kardex='"+fecha+"' "); 
			} catch (Exception ex) {
				System.out.println("Error en MetodoVerFormatos>>BuscarDesocultarSV "	+ ex);
			}
			return rs;
			
			
			
		}
		
		//fin buscar hic_desocultar_sv por nombre
		
		//buscar hic_signos_vitales
		
		public java.sql.ResultSet buscarSv(
				String nom_text,
				String fecha,
				String adm_paciente
				) {
			
			java.sql.ResultSet rs = null;
			Statement st = null;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT * FROM "+
						"hic_signos_vitales "+
						"WHERE "+
						"nom_text_sv = '"+nom_text+"' "+
						"AND adm_pac_fk='"+adm_paciente+"' "+
						"AND fecha_kardex='"+fecha+"' "); 
			} catch (Exception ex) {
				System.out.println("Error en MetodoVerFormatos>>BuscarSV "	+ ex);
			}
			return rs;
			
			
			
		}
		
		//fin buscar hic_signos_vitales
		
		
		//buscar hic_signos_vitales por admision y fecha
		public Vector<String> buscarSv(
				String fecha,
				String adm_paciente
				) {
			
			java.sql.ResultSet rs = null;
			Statement st = null;
			Vector <String> miVector = null;
			
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT nom_text_sv, contenido_text_sv " +
						"FROM hic_signos_vitales " +
						"WHERE " +
						"adm_pac_fk='"+adm_paciente+"' "+
						"AND fecha_kardex='"+fecha+"' "); 
				
			
				 miVector = new Vector<String>();
					
				while(rs.next()) {

					miVector.add(rs.getString(1));
					miVector.add(rs.getString(2));
				}
				
			} catch (Exception ex) {
				System.out.println("Error en MetodoVerFormatos>>BuscarSV2 "	+ ex);
			}
			System.out.println("vector ent"+miVector);
			return miVector;
			
			
			
		}
		
		//fin buscar hic_signos_vitales por admision y fecha
		
		
		//metodo para modificar hic_signos_vitales
		
		public boolean modificarSv(
		String nom_text,
		String contenido,
		String cod_usuario, 
		String fecha,
		String hora, 
		String adm_paciente,
		String fecha_kardex
		) {
			
		 PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
							"UPDATE hic_signos_vitales "+
							"SET "+
							"contenido_text_sv = ?, usuario_fk=?, hora=?, fecha=? "+
	 						"WHERE nom_text_sv = '"+nom_text+"'"+
					        " AND adm_pac_fk='"+adm_paciente+"'" +
					        "AND fecha_kardex ='"+fecha_kardex+"'");
					psc.setString(1, contenido);
					psc.setString(2, cod_usuario);
					psc.setString(3, hora);
					psc.setString(4, fecha);
					psc.executeUpdate();
					psc.close();
					con.cerrar();
					return true;
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					System.out.println("Error en MetodoVerFormatos>>ModificarSV "	+ ex);
					return false;
				}
			
		}
		
		//fin metodo para modificar hic_signos_vitales


	//metodo para guardar hic_signos_vitales
		
		public boolean insertarSv(
				String nom_text,
				String contenido,
				String cod_paciente,
				String cod_usuario, 
				String fecha,
				String hora, 
				String adm_paciente,
				String fecha_kardex,
				String estado
				
				) {

			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO hic_signos_vitales"+
			           "(nom_text_sv," +
			           "contenido_text_sv," +
			           "cod_pac_fk," +
			           "usuario_fk," +
			           "fecha," +
			           "hora," +
			           "adm_pac_fk," +
			           "fecha_kardex, "+
			           "estado " +
			           
			           ")" +
			           " VALUES(?,?,?,?,?,?,?,?,?)");
				psc.setString(1, nom_text);
				psc.setString(2, contenido);
				psc.setString(3, cod_paciente);
				psc.setString(4, cod_usuario);
				psc.setString(5, fecha);
				psc.setString(6, hora);
				psc.setString(7, adm_paciente);
				psc.setString(8, fecha_kardex);
				psc.setString(9, estado);
				
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				System.out.println("Error en MetodoVerFormatos>>InsertarSV "	+ ex);
				return false;
			}
		}
		
		//fin metodo para guardar hic_signos_vitales


		
	//signos vitales##############
	
	public void ActualizarEstadoOrden(String CodOrdenLab){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_orden SET estado=1 WHERE codigo="+CodOrdenLab+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>ActualizarEstadoOrden "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ObtenerAreasFormato(String CodFormato){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT har.codigo,har.nombre_area,hfr.nombre_formato FROM hic_formato hfr,hic_area har,hic_formato_area hfa WHERE hfr.codigo=hfa.codigo_formato_fk AND har.codigo=hfa.codigo_area_fk AND hfr.codigo="+CodFormato+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>ObtenerAreasFormato "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet NombreFormatos(String CodFormato){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM hic_formato where codigo="+CodFormato+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>CargarFormatos "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet CargarFormatos(){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM hic_formato ORDER BY nombre_formato");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>CargarFormatos "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet BuscarImagenologiaExamenes(){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM img_subexa ORDER BY nombre");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarImagenologiaExamenes "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet BuscarUsuariosFormatosContingencia(){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(sdp.nombre,' ',sdp.apellido) AS Usuario,sdp.profesion,su.usu_codigo FROM seg_datos_personales sdp,seg_usuario su WHERE sdp.dat_codigo=su.dat_codigo_fk AND (sdp.profesion='Medico General' OR sdp.profesion='Especialista' OR sdp.profesion='Enfermera' OR sdp.profesion='Fisioterapeuta' OR sdp.profesion='Nutricionista' OR sdp.profesion='Sicologia' ) ORDER BY Usuario");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarUsuariosFormatosContingencia "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet PacienteContingencia(String TipoDocumento,String NumeroDocumento){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT ap.pac_codigo_paciente,aad.adm_numero_ingreso,aad.fecha_registro,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombrePaciente, ae.nombre_entidad FROM adm_admisiones aad,adm_paciente ap,adm_entidad ae,adm_convenio acv WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ae.ent_nit=acv.ent_nit_contratante_fk AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato  ORDER BY aad.fecha_registro DESC ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>PacienteContingencia "+ex);
	        }	
	        return rs;	
	 }
	
	
	
	public java.sql.ResultSet ObtenerOrdenLabPendiente(String CodUsuario,String CodAdm){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM hic_orden WHERE CodUsu_fk="+CodUsuario+" AND TipoOrden=1 AND estado=0 AND CodAdm_fk="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>ObtenerOrdenLabPendiente "+ex);
	        }	
	        return rs;	
	 }
	
	
	
	public java.sql.ResultSet ObtenerOrdenProcedimientoPendiente(String CodUsuario,String CodAdm){
		 java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM hic_orden WHERE CodUsu_fk="+CodUsuario+" AND TipoOrden=5 AND estado=0 AND CodAdm_fk="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>ObtenerOrdenImgPendiente "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet ObtenerOrdenImgPendiente(String CodUsuario,String CodAdm){
		 java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM hic_orden WHERE CodUsu_fk="+CodUsuario+" AND TipoOrden=2 AND estado=0 AND CodAdm_fk="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>ObtenerOrdenImgPendiente "+ex);
	        }	
	        return rs;	
	 }
	
	
	public void ActualizarDetalleOrden(String codFormulacion,String DetOrden){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_formulacion SET observacion='"+DetOrden+"' WHERE codigo="+codFormulacion+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>ActualizarDetalleOrden "+ex);
	     	ex.getMessage();	     
	     }	
	 }	
	
	
	public java.sql.ResultSet MedicamentoRepetidoEnFormulacion(String codFormulacion,String CodMedicamento){	   
		/**
		 * busca si el medicamento esta repetido en la misma formulacion
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM hic_detalle_formulacion WHERE CodFormulacion_fk="+codFormulacion+" AND CodMedicamento_fk="+CodMedicamento+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>MedicamentoRepetidoEnFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstadoFormulacion(String codFormulacion){	   
		/**
		 * busca el estado de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE hfrc.codigo="+codFormulacion+" and hfrc.estado='-1' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerEstadoFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstadoFormulacionCE(String codFormulacion){	   
		/**
		 * busca el estado de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM agm_formulacion hfrc WHERE hfrc.codigo="+codFormulacion+" and hfrc.estado='-1' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerEstadoFormulacion "+ex);
        }	
        return rs;
    }
	
	public void ActivarFormulacion(String codFormulacion,String Estado){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_formulacion SET estado='"+Estado+"' WHERE codigo="+codFormulacion+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>ActivarFormulacion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActivarFormulacionCE(String codFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE agm_formulacion SET estado=0 WHERE codigo="+codFormulacion+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>ActivarFormulacion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActivarDetalleFormulacion(String codFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_detalle_formulacion SET estado=0 WHERE CodFormulacion_fk="+codFormulacion+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>ActivarDetalleFormulacion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActivarDetalleFormulacionCE(String codFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE agm_detalle_formulacion SET estado=0 WHERE CodFormulacion_fk="+codFormulacion+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>ActivarDetalleFormulacionCE "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirFormulacion(String codFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_formulacion WHERE codigo="+codFormulacion+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>OmitirFormulacion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirFormulacionCE(String codFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM agm_formulacion WHERE codigo="+codFormulacion+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>OmitirFormulacionCE "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDetalleFormulacionCE(String codDetFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM agm_detalle_formulacion WHERE codigo="+codDetFormulacion+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>OmitirDetalleFormulacionCE "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void SuspenderMedicamento(String codDetFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_detalle_formulacion SET estado='2' WHERE codigo="+codDetFormulacion+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>SuspenderMedicamento "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDetalleFormulacion(String codDetFormulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_detalle_formulacion WHERE codigo="+codDetFormulacion+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO MetodoVerFormatos>>OmitirDetalleFormulacion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CrearFormulacion(String codPac,String codAdm,String observacion,String estado,String usuario,String codCama,String codArea,String codSubarea,String hora,String fecha){
		/**
		 * creamos la formulacion
		 */
	   Formulacion frc = new Formulacion();
	   frc.setCodPac(codPac);
	   frc.setCodAdm(codAdm);
	   frc.setObservacion(observacion);
	   frc.setEstado(estado);
	   frc.setUsuario(usuario);
	   frc.setCodCama(codCama);
	   frc.setCodArea(codArea);
	   frc.setCodSubarea(codSubarea);
	   //frc.setTime(hora);
	   ///frc.setFecha(fecha);	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_formulacion(CodPac_fk,CodAdm_fk,observacion,estado,CodUsu_fk,CodCama_fk,CodArea_fk,CodSubarea_fk,hora,fecha)VALUES(?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, frc.getCodPac());
			    ps.setString(2, frc.getCodAdm());
			    ps.setString(3, frc.getObservacion());
			    ps.setString(4, frc.getEstado());
			    ps.setString(5, frc.getUsuario());
			    ps.setString(6, frc.getCodCama());
			    ps.setString(7, frc.getCodArea());
			    ps.setString(8, frc.getCodSubarea());
			    ps.setString(9, hora);
			    ps.setString(10,fecha);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearFormulacion "+ex);
			}
		}
	
	public void CrearImagenologia(String codigoPac_fk,
			String codigoExa_fk,
			String fecha,
			String hora,
			String estado,
			String resultado,
			String asignar_cita,
			String realizadopor,
			String aprobacion,
			String insercion,
			String fecha_cita,
			String hora_cita,
			String datosClinicos,
			String diagnostico,
			String portatil,
			String cedpac_fk){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO img_citas_espera(codigoPac_fk," +
			    		"codigoExa_fk," +
			    		"fecha," +
			    		"hora," +
			    		"estado," +
			    		"resultado," +
			    		"asignar_cita," +
			    		"realizadopor," +
			    		"aprobacion," +
			    		"insercion," +
			    		"fecha_cita," +
			    		"hora_cita," +
			    		"datosClinicos," +
			    		"diagnostico," +
			    		"portatil," +
			    		"cedpac_fk)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, codigoPac_fk);
			    ps.setString(2, codigoExa_fk);
			    ps.setString(3,fecha);
			    ps.setString(4, hora);
			    ps.setString(5, estado);
			    ps.setString(6, resultado);
			    ps.setString(7, asignar_cita);
			    ps.setString(8, realizadopor);
			    ps.setString(9, aprobacion);
			    ps.setString(10, insercion);
			    ps.setString(11, fecha_cita);
			    ps.setString(12, hora_cita);
			    ps.setString(13, datosClinicos);
			    ps.setString(14, diagnostico);
			    ps.setString(15, portatil);
			    ps.setString(16, cedpac_fk);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearImagenologia "+ex);
			}

		}
	
	public void CrearDetalleFormulacion(String codFormulacion_fk,String codigoMed,String cantidad,String dosificacion,String observacion,String estado,String CantLetra,String VigeTto,String CantDosis,String UniDosis,String CodUsu_fk ){
		/**
		 * creamos el Detalle formulacion
		 */
	   Formulacion frc = new Formulacion();
	   frc.setCodFormulacion_fk(codFormulacion_fk);
	   frc.setCodigoMed(codigoMed);
	   frc.setCantidad(cantidad);
	   frc.setDosificacion(dosificacion);
	   frc.setObservacion(observacion);
	   frc.setEstado(estado);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_detalle_formulacion(CodFormulacion_fk,CodMedicamento_fk,cantidad,dosis,observacion,estado,cantidad_letras,VigenciaTto,cant_dosis,unidad_dosis,CodUsu_fk)VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, frc.getCodFormulacion_fk());
			    ps.setString(2, frc.getCodigoMed());
			    ps.setString(3, frc.getCantidad());
			    ps.setString(4, frc.getDosificacion());
			    ps.setString(5, frc.getObservacion());
			    ps.setString(6, frc.getEstado());
			    ps.setString(7, CantLetra);
			    ps.setString(8, VigeTto);
			    ps.setString(9, CantDosis);
			    ps.setString(10, UniDosis);
			    ps.setString(11, CodUsu_fk);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearDetalleFormulacion "+ex);
			}

		}
	
	
	public void CrearDetalleFormulacionK(String codFormulacion_fk,String codigoMed,String cantidad,String dosificacion,String observacion,String estado,String CantLetra,String VigeTto,String CantDosis,String UniDosis,String Fecha,String Hora,String ObserInfu,String CodUsuK,String ObservacionMedKard ){
		/**
		 * creamos el Detalle formulacion
		 */
	   Formulacion frc = new Formulacion();
	   frc.setCodFormulacion_fk(codFormulacion_fk);
	   frc.setCodigoMed(codigoMed);
	   frc.setCantidad(cantidad);
	   frc.setDosificacion(dosificacion);
	   frc.setObservacion(observacion);
	   frc.setEstado(estado);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_detalle_formulacion(CodFormulacion_fk,CodMedicamento_fk,cantidad,dosis,observacion,estado,cantidad_letras,VigenciaTto,cant_dosis,unidad_dosis,fecha_ingreso,hora_ingreso,detalle_infusion,CodUsu_fk)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, frc.getCodFormulacion_fk());
			    ps.setString(2, frc.getCodigoMed());
			    ps.setString(3, frc.getCantidad());
			    ps.setString(4, frc.getDosificacion());
			    ps.setString(5, frc.getObservacion());
			    ps.setString(6, frc.getEstado());
			    ps.setString(7, CantLetra);
			    ps.setString(8, VigeTto);
			    ps.setString(9, CantDosis);
			    ps.setString(10, UniDosis);
			    ps.setString(11, Fecha);
			    ps.setString(12, Hora);
			    ps.setString(13, ObserInfu);
			    ps.setString(14, CodUsuK);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearDetalleFormulacionK "+ex);
			}

		}
	
	public void CrearFormulacionCE(String codPac,String codCita,String observacion,String estado,String usuario,Time hora,Date fecha){
		/**
		 * creamos la formulacion CE
		 */
	    
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO agm_formulacion(CodPac_fk,CodCita_fk,observacion,estado,CodUsu_fk,hora,fecha)VALUES(?,?,?,?,?,?,?)");
			    ps.setString(1, codPac);
			    ps.setString(2, codCita);
			    ps.setString(3, observacion);
			    ps.setString(4, estado);
			    ps.setString(5, usuario);
			    ps.setTime(6, hora);
			    ps.setDate(7,fecha);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearFormulacionCE "+ex);
			}
		}
	public void CrearDetalleFormulacionCE(String codFormulacion_fk,String codigoMed,String cantidad,String dosificacion,String observacion,String estado){
		/**
		 * creamos el Detalle formulacion CEX
		 */
	  	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO agm_detalle_formulacion(CodFormulacion_fk,CodMedicamento_fk,cantidad,dosis,observacion,estado)VALUES(?,?,?,?,?,?)");
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
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearDetalleFormulacionCE "+ex);
			}

		}
	
	public void IngresarHoraDosificacion(String CodDetForm_FK,String fecha_dosis,String hora_dosis,String CodUsuIns_FK){
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement("INSERT INTO hic_horas_dosificacion(CodDetForm_FK,fecha_dosis,hora_dosis,CodUsuIns_FK)VALUES(?,?,?,?)");
			ps.setString(1, CodDetForm_FK);
			ps.setString(2, fecha_dosis);
			ps.setString(3, hora_dosis);
			ps.setString(4, CodUsuIns_FK);
				    		 
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoVerFormatos>>CrearDosificacion "+ex);
		}
	}
	
	public void CrearDispenacion(String codDetFormulacion_fk){
	   Formulacion frc = new Formulacion();
	   frc.setCodDetFormulacion_fk(codDetFormulacion_fk);
	 
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO farc_dispensacion(cod_detalle_fk)VALUES(?)");
			    ps.setString(1, frc.getCodDetFormulacion_fk());
			    		 
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearDosificacion "+ex);
			}

		}
	
	
	public java.sql.ResultSet ObtenerHorasDispensacionMedicamento(String CodDetalleFormulacionK){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hhd.*,LEFT(hora_dosis,2) FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        	System.out.println("SELECT hhd.*,LEFT(hora_dosis,2) FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        	//System.out.println("SELECT hhd.*,LEFT(hora_dosis,2) FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerHorasDispensacionMedicamento "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerCodigoHorasDispensacionMedicamentoEliminar(String CodDetalleFormulacionK){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_horas_dosificacion WHERE CodDetForm_FK='"+CodDetalleFormulacionK+"' AND (estado='1' or estado='2') ");
        	System.out.println("SELECT * FROM hic_horas_dosificacion WHERE CodDetForm_FK='"+CodDetalleFormulacionK+"' AND (estado='1' or estado='2') ");
        	//System.out.println("SELECT hhd.*,LEFT(hora_dosis,2) FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoHorasDispensacionMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DiaSiguiente(){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DATE_ADD(CURDATE(), INTERVAL 1 DAY) AS diaSGT ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>DiaSiguiente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoHorasDispensacionMedicamento(String CodDetalleFormulacionK){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_horas_dosificacion WHERE CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        	System.out.println("SELECT * FROM hic_horas_dosificacion WHERE CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        	//System.out.println("SELECT hhd.*,LEFT(hora_dosis,2) FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoHorasDispensacionMedicamento "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerCodigoHorasDispensacionMedicamento(String CodDetalleFormulacionK,String Hora){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_horas_dosificacion WHERE CodDetForm_FK='"+CodDetalleFormulacionK+"' AND hora_dosis='"+Hora+"'");
        	System.out.println("SELECT * FROM hic_horas_dosificacion WHERE CodDetForm_FK='"+CodDetalleFormulacionK+"' AND hora_dosis='"+Hora+"'");
        	//System.out.println("SELECT hhd.*,LEFT(hora_dosis,2) FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetalleFormulacionK+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoHorasDispensacionMedicamento "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerCodigoFormulacionCE(Time hora,Date fecha){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM agm_formulacion hfrc WHERE hfrc.hora='"+hora+"' AND hfrc.fecha='"+fecha+"'  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoFormulacionCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoFormulacionKardesActiva(String CodAdm,String fecha){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE  CodAdm_fk='"+CodAdm+"' AND estado='2' AND fecha='"+fecha+"' ");
        	System.out.println("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE  CodAdm_fk='"+CodAdm+"' AND estado='2' AND fecha='"+fecha+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoFormulacionKardes "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerHorasDispensacion(String Frecuencia){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_horas_dosis WHERE frecuenciaFK='"+Frecuencia+"' ");
        	System.out.println("SELECT * FROM hic_horas_dosis WHERE frecuenciaFK='"+Frecuencia+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerHorasDispensacion "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet ObtenerCodigoFormulacionKardesSeleccionado(String fecha,String CodAdm,String estado){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE  hfrc.fecha='"+fecha+"' AND CodAdm_fk='"+CodAdm+"' AND estado='"+estado+"' ");
        	System.out.println("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE  hfrc.fecha='"+fecha+"' AND CodAdm_fk='"+CodAdm+"' AND estado='"+estado+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoFormulacionKardesSeleccionado "+ex);
        }	
        return rs;
    }
	
	
	
	
	public java.sql.ResultSet ObtenerCodigoFormulacionKardes(Time hora,Date fecha,String CodAdm){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE hfrc.hora='"+hora+"' AND hfrc.fecha='"+fecha+"' AND CodAdm_fk='"+CodAdm+"' AND estado='4' ");
        	//System.out.println("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE hfrc.hora='"+hora+"' AND hfrc.fecha='"+fecha+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoFormulacionKardes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoFormulacion(Time hora,Date fecha,String CodAdm){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE hfrc.hora='"+hora+"' AND hfrc.fecha='"+fecha+"' AND CodAdm_fk='"+CodAdm+"' ");
        	//System.out.println("SELECT hfrc.codigo FROM hic_formulacion hfrc WHERE hfrc.hora='"+hora+"' AND hfrc.fecha='"+fecha+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormulaPendUsu(String codAdm,String usuario){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfc.codigo,hfc.observacion FROM hic_formulacion hfc WHERE hfc.CodAdm_fk="+codAdm+" AND hfc.estado=-1 AND hfc.CodUsu_fk="+usuario+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerFormulaPendUsu "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DetalledeFormulacionK(String CodigoFormulacion){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT( med.nombre,' ', med.concentracion,' ',fu.sigla) AS NomMedicamento,CONCAT(hdf.cant_dosis, ' ', fu.sigla) cant_dosis,hdf.cantidad,hdf.codigo,SUBSTRING_INDEX(hdf.dosis, '.', - 1) via_admin,SUBSTRING_INDEX(SUBSTRING_INDEX(hdf.dosis, '.', 1),' ',- 3) AS frecuencia,hdf.estado,CONCAT(sdp.nombre,' ',sdp.apellido,'-(',sdp.profesion,'-',sdp.ocupacion,')',hdf.fecha_ingreso,'/',hdf.hora_ingreso ) AS Medico,hdf.estado FROM hic_detalle_formulacion hdf,medicamentos med,farc_unidades fu,seg_usuario su,seg_datos_personales sdp WHERE hdf.CodMedicamento_fk = med.codigo AND fu.codigo = med.cod_unidadFK  AND hdf.CodFormulacion_fk = '"+CodigoFormulacion+"' AND su.usu_codigo=hdf.CodUsu_fk  AND sdp.dat_codigo=su.dat_codigo_fk ORDER BY hdf.estado ASC ");
        	System.out.println("SELECT CONCAT( med.nombre,' ', med.concentracion,' ',fu.sigla) AS NomMedicamento,CONCAT(hdf.cant_dosis, ' ', fu.sigla) cant_dosis,hdf.cantidad,hdf.codigo,SUBSTRING_INDEX(hdf.dosis, '.', - 1) via_admin,SUBSTRING_INDEX(SUBSTRING_INDEX(hdf.dosis, '.', 1),' ',- 3) AS frecuencia,hdf.estado,CONCAT(sdp.nombre,' ',sdp.apellido,'-(',sdp.profesion,'-',sdp.ocupacion,')',hdf.fecha_ingreso,'/',hdf.hora_ingreso ) AS Medico,hdf.estado FROM hic_detalle_formulacion hdf,medicamentos med,farc_unidades fu,seg_usuario su,seg_datos_personales sdp WHERE hdf.CodMedicamento_fk = med.codigo AND fu.codigo = med.cod_unidadFK  AND hdf.CodFormulacion_fk = '"+CodigoFormulacion+"' AND su.usu_codigo=hdf.CodUsu_fk  AND sdp.dat_codigo=su.dat_codigo_fk ORDER BY hdf.estado ASC ");
        	//System.out.println("SELECT CONCAT(med.nombre,' ',med.concentracion,' ',fu.sigla) AS NomMedicamento,CONCAT(hdf.cant_dosis,' ',fu.sigla) cant_dosis,hdf.cantidad,hdf.codigo,SUBSTRING_INDEX(hdf.dosis,'.',-1) via_admin,SUBSTRING_INDEX(SUBSTRING_INDEX(hdf.dosis,'.',1),' ',-3) AS frecuencia,hdf.estado FROM hic_detalle_formulacion hdf,medicamentos med,farc_unidades fu WHERE hdf.CodMedicamento_fk=med.codigo AND fu.codigo=med.cod_unidadFK AND hdf.CodFormulacion_fk="+CodigoFormulacion+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>DetalledeFormulacionK "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet DetalledeFormulacion(String CodigoFormulacion){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(med.nombre,'/',med.concentracion) AS NomMedicamento,hdf.dosis,hdf.observacion,hdf.cantidad,hdf.codigo FROM hic_detalle_formulacion hdf,medicamentos med WHERE hdf.CodMedicamento_fk=med.codigo AND hdf.CodFormulacion_fk="+CodigoFormulacion+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>DetalledeFormulacion "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet DetalledeFormulacionCE(String CodigoFormulacion){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(med.nombre,'/',med.concentracion) AS NomMedicamento,hdf.dosis,hdf.observacion,hdf.cantidad,hdf.codigo FROM agm_detalle_formulacion hdf,medicamentos med WHERE hdf.CodMedicamento_fk=med.codigo AND hdf.CodFormulacion_fk="+CodigoFormulacion+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>DetalledeFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoDetalleFormulacionK(String codFormulacion_fk,String codigoMed,String fecha,String hora){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdf.codigo,hdf.fecha_ingreso,hdf.hora_ingreso,hdf.dosis FROM hic_detalle_formulacion hdf WHERE hdf.CodFormulacion_fk="+codFormulacion_fk+" AND hdf.CodMedicamento_fk="+codigoMed+" and hdf.fecha_ingreso='"+fecha+"' and hdf.hora_ingreso='"+hora+"' ");
        	//System.out.println("SELECT hdf.codigo FROM hic_detalle_formulacion hdf WHERE hdf.CodFormulacion_fk="+codFormulacion_fk+" AND hdf.CodMedicamento_fk="+codigoMed+"  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoDetalleFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoDetalleFormulacion(String codFormulacion_fk,String codigoMed){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdf.codigo FROM hic_detalle_formulacion hdf WHERE hdf.CodFormulacion_fk="+codFormulacion_fk+" AND hdf.CodMedicamento_fk="+codigoMed+"  ");
        	System.out.println("SELECT hdf.codigo FROM hic_detalle_formulacion hdf WHERE hdf.CodFormulacion_fk="+codFormulacion_fk+" AND hdf.CodMedicamento_fk="+codigoMed+"  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoDetalleFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoDetalleFormulacionCE(String codFormulacion_fk,String codigoMed){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdf.codigo FROM agm_detalle_formulacion hdf WHERE hdf.CodFormulacion_fk="+codFormulacion_fk+" AND hdf.CodMedicamento_fk="+codigoMed+"  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCodigoDetalleFormulacionCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FormatosAtencionesAnterioresHospitalizacion(String CodAdm){	   
		/**
		 * busca los formatos de las atenciones anteriores 
		 * de la fecha de la admision seleccionada. 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hf.codigo,hf.nombre_formato,hf.repetido,hafp.codigo_adm_fk,hafp.fecha,hafp.hora,hafp.codigo_usu_fk,hafp.codigo_pac_fk,sdt.nombre,sdt.apellido  from hic_adm_formatos_pac hafp,adm_admisiones aad,adm_paciente apac,hic_formato hf,seg_datos_personales sdt,seg_usuario su where aad.adm_numero_ingreso=hafp.codigo_adm_fk and hafp.codigo_pac_fk=apac.pac_codigo_paciente and hf.codigo=hafp.codigo_for_fk and sdt.dat_codigo=su.dat_codigo_fk and su.usu_codigo=hafp.codigo_usu_fk and hafp.estado=1 and hafp.codigo_adm_fk='"+CodAdm+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>FormatosAtencionesAnterioresHospitalizacion "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet RestaFechas(String Fecha){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DATEDIFF(CURDATE(),'"+Fecha+"') AS DiffDate ");
        	System.out.println("SELECT DATEDIFF(CURDATE(),'"+Fecha+"') AS DiffDate ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>RestaFechas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarValoresSV(String Fecha,String CodAdm,String Hora){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  hpt.*, CONCAT(sdp.nombre,' ',sdp.apellido,' EL DIA ',hpt.fecha_insercion,' A LAS ',hpt.hora_insercion)UsuarioInserta FROM  hic_peso_talla hpt,seg_usuario su,seg_datos_personales sdp WHERE hpt.CodAdm = "+CodAdm+"  AND hpt.fecha = '"+Fecha+"'  AND hpt.hora = '"+Hora+"'  AND su.usu_codigo=hpt.CodUsu_fk AND su.dat_codigo_fk=sdp.dat_codigo");
        	System.out.println("SELECT  hpt.*, CONCAT(sdp.nombre,' ',sdp.apellido,' EL DIA ',hpt.fecha_insercion,' A LAS ',hpt.hora_insercion)UsuarioInserta FROM  hic_peso_talla hpt,seg_usuario su,seg_datos_personales sdp WHERE hpt.CodAdm = "+CodAdm+"  AND hpt.fecha = '"+Fecha+"'  AND hpt.hora = '"+Hora+"'  AND su.usu_codigo=hpt.CodUsu_fk AND su.dat_codigo_fk=sdp.dat_codigo");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ConsultarSV "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FormatosAtencionesAnterioresConsultaExterna(String CodHorarioMedico){	   
		/**
		 * busca los formatos de las atenciones anteriores CE
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.codigo,hf.nombre_formato,hf.repetido,afp.CodHorMedico_fk,afp.fecha,afp.hora,afp.cod_usu_fk,afp.cod_pac_fk,sdt.nombre,sdt.apellido  FROM agm_horariomedico ahm,agm_formatos_pac afp,adm_paciente apac,hic_formato hf,seg_datos_personales sdt,seg_usuario su WHERE ahm.codigo=afp.CodHorMedico_fk AND afp.cod_pac_fk=apac.pac_codigo_paciente AND hf.codigo=afp.cod_for_fk AND sdt.dat_codigo=su.dat_codigo_fk AND su.usu_codigo=afp.cod_usu_fk AND ahm.estado=3 AND afp.CodHorMedico_fk="+CodHorarioMedico+" ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>FormatosAtencionesAnterioresConsultaExterna "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarHistorialEpicrisis(String CodPac){	   
		/**
		 * busca las epicrisis anteriores que haya tenido el paciente llevando como parametro 
		 * el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,fecha from hic_epicrisis where codpac="+CodPac+" order by fecha desc  ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarHistorialEpicrisis "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarAtencionesAnterioresHospitalizacion(String CodPac){	   
		/**
		 * busca las atenciones anteriores que haya tenido el paciente llevando como parametro 
		 * el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select aad.adm_numero_ingreso,aad.fecha_registro,aad.hora_registro from adm_admisiones aad,adm_paciente apac where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and aad.estado=1 and apac.pac_codigo_paciente='"+CodPac+"' order by aad.fecha_registro asc ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarAtencionesAnterioresHospitalizacion "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDetalleMedicamento(String CodMedicamento){	   
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT med.codigoMedicamento,med.control,med.clasificacion,med.concentracion FROM medicamentos med WHERE med.codigo='"+CodMedicamento+"'  ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarDetalleMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarAtencionesAnterioresConsultaExterna(String CodPac){	   
		/**
		 * busca las atenciones anteriores que haya tenido el paciente llevando como parametro 
		 * el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT acp.codigo,ahm.fechas,ahm.codigo FROM agm_citaspacientes acp,adm_paciente apac,agm_horariomedico ahm WHERE apac.pac_codigo_paciente=acp.CodPac_fk AND ahm.codigo=acp.CodHorMedico_fk AND ahm.estado=3 AND apac.pac_codigo_paciente='"+CodPac+"' ORDER BY ahm.fechas ASC  ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarAtencionesAnterioresConsultaExterna "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarRelacionFormatoAdmision1(String CodFormato,String CodAdmision){	   
		/**
		 * buscar si el formato se puede repetir o no
		 * 1=no se puede
		 * 0=se puede
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from hic_adm_formatos_pac where codigo_adm_fk="+CodAdmision+" and codigo_for_fk="+CodFormato+"");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarRelacionFormatoAdmision1 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListadoUnidades(){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sigla FROM farc_unidades");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ListadoUnidades "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListarViasAdministracion(){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_via_admon");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ListarViasAdministracion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarDetalleFormulaciones(String CodFormula){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT med.nombre,hdf.dosis,hdf.codigo FROM hic_formulacion hf,hic_detalle_formulacion hdf,medicamentos med WHERE hf.codigo=hdf.CodFormulacion_fk AND hdf.CodMedicamento_fk=med.codigo AND hf.codigo='"+CodFormula+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ConsultarDetalleFormulaciones "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarCodigoFormulacion(String CodAdm,String Fecha,String Hora){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_formulacion WHERE CodAdm_fk='"+CodAdm+"' AND fecha='"+Fecha+"' AND hora='"+Hora+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ConsultarCodigoFormulacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCamaAreaSubarea(String CodAdm){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT acc.cen_numero_cama,acc.codsubarea,aa.codigo FROM adm_admisiones ad,adm_censo_cama acc,adm_area aa WHERE ad.cen_numero_cama_fk=acc.cen_numero_cama AND aa.nombre=acc.pabellon AND ad.adm_numero_ingreso='"+CodAdm+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarCamaAreaSubarea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListadoMedicamentosInsumos(){	   
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT med.codigo,CONCAT(med.nombre,'/',med.concentracion,' ',fu.sigla,' ',ff.descripcion) AS Medicamento FROM medicamentos med,farc_unidades fu,farc_formafarmaceutica ff WHERE med.estado=1 AND fu.codigo=med.cod_unidadFK AND ff.codigo=med.cod_formaFK ORDER BY med.nombre ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ListadoMedicamentosInsumos "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet ObtenerTipoFormato(String CodFormato){	   
		/**
		 * buscar si el formato se puede repetir o no
		 * 1=no se puede
		 * 0=se puede
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select repetido from hic_formato where codigo="+CodFormato+"");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerTipoFormato "+ex);
        }	
        return rs;
    }

public void GuardarFormatoHCcontingencia(String Fecha,String Hora,String CodAdm){
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE hic_adm_formatos_pac SET estado='1' WHERE fecha='"+Fecha+"' AND hora='"+Hora+"' AND codigo_adm_fk='"+CodAdm+"'");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoVerFormatos>>GuardarFormatoHCcontingencia "+ex);
     	ex.getMessage();	     
     }	
 }

public void GuardarDetalleFormatoHCcontingencia(String Fecha,String Hora,String CodAdm){
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE hic_resultado SET estado='1' WHERE fecha='"+Fecha+"' AND hora='"+Hora+"' AND codAdm_fk='"+CodAdm+"' ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoVerFormatos>>GuardarDetalleFormatoHCcontingencia "+ex);
     	ex.getMessage();	     
     }	
 }

public void OmitirDetalleFormulacionC(String CodDetFormulacion){
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("DELETE FROM hic_detalle_formulacion WHERE codigo='"+CodDetFormulacion+"'");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoVerFormatos>>OmitirDetalleFormulacionC "+ex);
     	ex.getMessage();	     
     }	
 }

	public void OmitirRegistrosFormato(String Fecha,String Hora,String CodAdm){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_resultado where fecha='"+Fecha+"' and hora='"+Hora+"' and codAdm_fk='"+CodAdm+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoVerFormatos>>OmitirRegistrosFormato "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirFormatoPaciente(String CodigoFormatoPaciente){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_adm_formatos_pac where codigo='"+CodigoFormatoPaciente+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoVerFormatos>>OmitirFormato "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
public java.sql.ResultSet ObtenerHoraFechaFormato(String CodigoFormatoPaciente){	   
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select fecha,hora,codigo_adm_fk from hic_adm_formatos_pac where codigo="+CodigoFormatoPaciente+"");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerHoraFechaFormato "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ObtenerUsuarioFormato(String CodArea,String FechaFormato,String HoraFormato,String CodUsuario,String CodAdm){	   
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codUsu_Fk,hres.estado from hic_resultado hres where hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codUsu_Fk='"+CodUsuario+"' and hres.codAdm_fk="+CodAdm+" ");
        	//System.out.println("select distinct hres.codUsu_Fk,hres.estado from hic_resultado hres where hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codUsu_Fk='"+CodUsuario+"' and hres.codAdm_fk="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerUsuarioFormato "+ex);
        }	
        return rs;
    }
		
	
	public ResultSet listarParaAutocompletarFormato(String cod,String CodigoUsuario) throws Exception {
		/**
		 * se buscan todos los formatos que estan en la base de datos
		 * tiene como parametro el nombre del formato.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT distinct hf.codigo,hf.nombre_formato FROM hic_formato hf,seg_usuariosformato suf,seg_usuario su WHERE hf.nombre_formato LIKE '"+cod+"%' and suf.codigoFormato_fk=hf.codigo and suf.codigoUsuario_fk=su.usu_codigo and su.usu_codigo='"+CodigoUsuario+"'");
	        return r;
	    }
	
	
	public ResultSet ObtenerNombreSubarea(String CodEstudio) throws Exception {
		/**
		 * se buscan todos los formatos que estan en la base de datos
		 * tiene como parametro el nombre del formato.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT nombre FROM lab_subarea WHERE codigo='"+CodEstudio+"'");
	        return r;
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
        	rs=st.executeQuery("select ha.codigo,ha.nombre_area,hafp.hora,hafp.fecha from hic_formato hf,hic_formato_area hfa,hic_area ha,hic_adm_formatos_pac hafp where hf.codigo=hfa.codigo_formato_fk and ha.codigo=hfa.codigo_area_fk and hf.codigo=hafp.codigo_for_fk and hf.codigo='"+CodFormato+"' and hafp.fecha='"+fecha+"' and hafp.hora='"+hora+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerAreaFormato "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerTipoUsuario(String CodUsuario){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select sdt.profesion from seg_datos_personales sdt, seg_usuario su where sdt.dat_codigo=su.dat_codigo_fk and su.usu_codigo="+CodUsuario+"");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerTipoUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEncabezadoFactura(String CodAdmision){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select  ef.cod_enc_factura,cod_eps from fact_enc_factura ef where ef.cod_admision="+CodAdmision+"");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerEncabezadoFactura "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPrograma(String CodEps,String CodReferencia){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select p.cod_programa, p.cod_referencia, p.descripcion, cs.descripcion as ClaseServicio,ft.valor from fact_manuales_tarifarios mt, fact_convenios fc, fact_programas p, fact_tarifas ft, fact_clases_servicio cs where (mt.cod_manual_tarifario=fc.cod_tarifa or mt.cod_manual_tarifario=fc.cod_tarifa_adicional) and (ft.manual_tarifario=fc.cod_tarifa or ft.manual_tarifario=fc.cod_tarifa_adicional) and (mt.cod_manual_tarifario=ft.manual_tarifario or mt.cod_manual_tarifario=ft.manual_tarifario) and cod_entidad="+CodEps+" and p.manual_base=mt.manual_base and p.cod_programa=ft.programa and p.clase_servicio=cs.cod_clase_servicio and p.cod_referencia="+CodReferencia+" ");
        	System.out.println("select p.cod_programa, p.cod_referencia, p.descripcion, cs.descripcion as ClaseServicio,ft.valor from fact_manuales_tarifarios mt, fact_convenios fc, fact_programas p, fact_tarifas ft, fact_clases_servicio cs where (mt.cod_manual_tarifario=fc.cod_tarifa or mt.cod_manual_tarifario=fc.cod_tarifa_adicional) and (ft.manual_tarifario=fc.cod_tarifa or ft.manual_tarifario=fc.cod_tarifa_adicional) and (mt.cod_manual_tarifario=ft.manual_tarifario or mt.cod_manual_tarifario=ft.manual_tarifario) and cod_entidad="+CodEps+" and p.manual_base=mt.manual_base and p.cod_programa=ft.programa and p.clase_servicio=cs.cod_clase_servicio and p.cod_referencia="+CodReferencia+" ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerPrograma "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNombreFormato(String CodFormato){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre_formato from hic_formato where codigo="+CodFormato+"");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerNombreFormato "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerificarFormatoConHoraFechaIgual(String Fecha,String Hora){
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_formatos_pac afp WHERE afp.fecha='"+Fecha+"' AND afp.hora='"+Hora+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>VerificarFormatoConHoraFechaIgual "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet ObtenerAreaFormatoCE(String CodFormato,String Fecha,String Hora){
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
        	rs=st.executeQuery("SELECT ha.codigo,ha.nombre_area,afp.hora,afp.fecha FROM hic_formato hf,hic_formato_area hfa,hic_area ha,agm_formatos_pac afp WHERE hf.codigo=hfa.codigo_formato_fk AND ha.codigo=hfa.codigo_area_fk AND hf.codigo=afp.cod_for_fk AND hf.codigo='"+CodFormato+"' AND afp.fecha='"+Fecha+"' AND afp.hora='"+Hora+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerAreaFormatoCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerAreaFormato2(String CodFormato,String FechaFormato,String HoraFormato,String CodAdm){
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
        	rs=st.executeQuery("select ha.codigo,ha.nombre_area,hafp.hora,hafp.fecha,hafp.codigo_usu_fk,hafp.codigo_pac_fk from hic_formato hf,hic_formato_area hfa,hic_area ha,hic_adm_formatos_pac hafp where hf.codigo=hfa.codigo_formato_fk and ha.codigo=hfa.codigo_area_fk and hf.codigo=hafp.codigo_for_fk and hf.codigo='"+CodFormato+"' and hafp.fecha='"+FechaFormato+"' and hafp.hora='"+HoraFormato+"' and hafp.codigo_adm_fk='"+CodAdm+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerAreaFormato "+ex);
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
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hp.codigo as CodPregunta,ha.codigo as CodArea,hp.patronNormal from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPreguntasArea "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerPreguntasAreaLlenoM(String CodArea,String FechaFormato,String HoraFormato,String CodAdm){	   
		/**
		 * se obtienen las preguntas del area tiene como parametro el codigo del area
		 * y devuelve como respuesta el nombre de la pregunta, el tipo de respuesta de la pregunta,y el codigo del tipo de respuesta
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad,hafp.codigo from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,hic_resultado hres,hic_adm_formatos_pac hafp  where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and ha.codigo='"+CodArea+"' and hres.codAdm_fk="+CodAdm+" and hafp.fecha=hres.fecha and hafp.hora=hres.hora");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPreguntasArea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPreguntasAreaLleno(String CodArea,String FechaFormato,String HoraFormato,String CodAdm){	   
		/**
		 * se obtienen las preguntas del area tiene como parametro el codigo del area
		 * y devuelve como respuesta el nombre de la pregunta, el tipo de respuesta de la pregunta,y el codigo del tipo de respuesta
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,hic_resultado hres where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and ha.codigo='"+CodArea+"' and hres.codAdm_fk="+CodAdm+" ");
        	//System.out.println("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,hic_resultado hres where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and ha.codigo='"+CodArea+"' and hres.codAdm_fk="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPreguntasArea "+ex);
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
        	System.out.println("Error en Metodo ObtenerAreaFormato "+ex);
        }	
        return rs;
    }
	
	public ResultSet listarPacientesPorAtender(String cod) throws Exception {
		/**
		 * se buscan todos los pacientes que tienen admision en la base de datos
		 * tiene como parametro el nombre del paciente.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT distinct ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,aa.adm_numero_ingreso from adm_paciente ap,adm_admisiones aa where ap.pac_codigo_paciente=aa.pac_codigo_paciente_fk and aa.atendido=0 and aa.estado=0 and ap.nombre LIKE '"+cod+"%'");
	        return r;
	    }
	
	public ResultSet PacientesPorAtender() throws Exception {
		/**
		 * se buscan todos los pacientes que tienen admision en la base de datos
		 * tiene como parametro el nombre del paciente.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,aa.adm_numero_ingreso from adm_paciente ap,adm_admisiones aa where ap.pac_codigo_paciente=aa.pac_codigo_paciente_fk and aa.atendido=0 and aa.estado=0 ");
	        return r;
	    }
	
	public java.sql.ResultSet ObtenerPaciente(String CodPac){
		/**
		 * se obtienen los datos del paciente para la mostrarlos como infromacion
		 * lleva como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre,primer_apellido,segundo_apellido,genero,tipo_documento,numero_documento, (year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad from adm_paciente where pac_codigo_paciente='"+CodPac+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPaciente "+ex);
        }	
        return rs;
    }
	
	
	public void InsertarSignosVitales(String CodAdm,String peso,String talla,String ta,String fc,String fr,String pulso,String fecha,String hora,String fecha_insercion,String hora_insercion,String CodUsu_fk){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_peso_talla(CodAdm,peso,talla,ta,fc,fr,pulso,fecha,hora,fecha_insercion,hora_insercion,CodUsu_fk)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, CodAdm);
			    ps.setString(2, peso);
			    ps.setString(3, talla);
			    ps.setString(4, ta);
			    ps.setString(5, fc);
			    ps.setString(6, fr);
			    ps.setString(7, pulso);
			    ps.setString(8, fecha);
			    ps.setString(9, hora);
			    ps.setString(10, fecha_insercion);
			    ps.setString(11, hora_insercion);
			    ps.setString(12, CodUsu_fk);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>InsertarSignosVitales "+ex);
			}

		}
	
	public void RelacionFormatoAdmisionPaciente(String CodFormato,String CodAdmision,
			String CodPaciente,String hora,String fecha,String CodUsuario){
		/**
		 * creamos la relacion de los formatos con la admision y el paciente 
		 * lleva como parametro el codigo del formato,codigo de la admision,codigo del paciente
		 */
	   CrearFormatoHic cfa = new CrearFormatoHic();
	   cfa.setcodigo_adm_fk(CodAdmision);
	   cfa.setcodigo_pac_fk(CodPaciente);
	   cfa.setcodigo_formato_fk(CodFormato);
	   cfa.setCodUsuario(CodUsuario);
	  // cfa.setHora(hora);
	  // cfa.setFecha(fecha);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into hic_adm_formatos_pac(codigo_adm_fk,codigo_pac_fk,codigo_for_fk,fecha,hora,codigo_usu_fk)values(?,?,?,?,?,?)");
			    ps.setString(1, cfa.getcodigo_adm_fk());
			    ps.setString(2, cfa.getcodigo_pac_fk());
			    ps.setString(3, cfa.getcodigo_formato_fk());
			    ps.setString(4, fecha);
			    ps.setString(5, hora);
			    ps.setString(6, cfa.getCodUsuario());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>RelacionFormatoAdmisionPaciente "+ex);
			}

		}
	
	public void InsertarDetalleFactura(String fecha,String hora,String referencia,
			String cod_programa_fk,String nombre_programa,String clase_servicio,
			String fecha_realizacion,String cantidad,String valor,
			String cod_usuario,String cod_enc_factura_fk,String cod_medico){
	/*System.out.print("fecha "+fecha+" hora "+hora+" referencia "+referencia+" cod_programa_fk "+cod_programa_fk
			+" nombre_programa "+nombre_programa+" clase_servicio "+clase_servicio+" fecha_realizacion "+fecha_realizacion
			+" cantidad "+cantidad+" valor "+valor+" cod_usuario "+cod_usuario+" cod_enc_factura_fk "+cod_enc_factura_fk
			+" cod_medico "+cod_medico);*/
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_det_factura (fecha,hora,referencia,cod_programa_fk,nombre_programa,clase_servicio,fecha_realizacion,cantidad,valor,cod_usuario,cod_enc_factura_fk,cod_medico)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,fecha );
			    ps.setString(2,hora );
			    ps.setString(3,referencia );
			    ps.setString(4,cod_programa_fk );
			    ps.setString(5,nombre_programa );
			    ps.setString(6,clase_servicio );
			    ps.setString(7,fecha_realizacion );
			    ps.setString(8,cantidad );
			    ps.setString(9,valor );
			    ps.setString(10,cod_usuario);
			    ps.setString(11,cod_enc_factura_fk);
			    ps.setString(12,cod_medico);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>InsertarDetalleFactura "+ex);
			}

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
        	rs=st.executeQuery("select hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora  from hic_formato hf,adm_admisiones aa,adm_paciente ap,hic_adm_formatos_pac hafp where hf.codigo=hafp.codigo_for_fk and aa.adm_numero_ingreso=hafp.codigo_adm_fk and ap.pac_codigo_paciente =hafp.codigo_pac_fk and hafp.codigo_pac_fk='"+CodPaciente+"' and hafp.codigo_adm_fk='"+CodAdmision+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPaciente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosPacienteCE(String CodPaciente){
		/**
		 * se obtienen los datos del paciente para la mostrarlos como infromacion
		 * lleva como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.codigo,hf.nombre_formato,afp.fecha,afp.hora FROM hic_formato hf,adm_paciente ap,agm_formatos_pac afp WHERE hf.codigo=afp.cod_for_fk AND ap.pac_codigo_paciente =afp.cod_pac_fk AND afp.cod_pac_fk='"+CodPaciente+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPacienteCE "+ex);
        }	
        return rs;
    }
	
	
	public ResultSet Pacientes() throws Exception {
		/**
		 * se buscan todos los pacientes que tienen  la base de datos
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select tipo_documento,numero_documento,primer_apellido,segundo_apellido,nombre,pac_codigo_paciente from adm_paciente order by nombre");
	        return r;
	    }
	public ResultSet listarPacientes(String cod) throws Exception {
		/**
		 * se buscan todos los pacientes que tiene la base de datos
		 * tiene como parametro el nombre del paciente.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT tipo_documento,numero_documento,primer_apellido,segundo_apellido,nombre,pac_codigo_paciente from adm_paciente where nombre LIKE '"+cod+"%' order by nombre");
	        return r;
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
	
	
	public void RelacionPacienteLaboratorios(String CodEstudio,String TipoEstudio,String CodPaciente,String hora,String fecha){
		/**
		 * creamos la relacion de los formatos con la admision y el paciente 
		 * lleva como parametro el codigo del formato,codigo de la admision,codigo del paciente
		 */
		CrearPacLab cpl = new CrearPacLab();
		cpl.setCodigoEstudio(CodEstudio);
		cpl.setCodigoPac(CodPaciente);
		cpl.setTipoEstudio(TipoEstudio);
		cpl.setHora(hora);
		cpl.setFecha(fecha);
	 		
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into ord_pac_lab(codigo_pac_fk,codigo_estudio,tipo_estudio,hora,fecha)values(?,?,?,?,?)");
			    ps.setString(1,cpl.getCodigoPac());
			    ps.setString(2,cpl.getCodigoEstudio());
			    ps.setString(3,cpl.getTipoEstudio());
			    ps.setString(4,cpl.getHora());
			    ps.setString(5,cpl.getFecha());
			
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ RelacionPacienteLaboratorios "+ex);
			}

		}
	
	
	public java.sql.ResultSet ObtenerExamenesGrupo(String CodSubArea){
		/**
		 * se obtienen los examenes del grupo seleccionado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lex.codigo,lex.nombre,lex.tipo,lra.valorinicial,lra.valorfinal,lu.nombre,lsa.nombre from lab_examen lex,lab_subarea lsa,lab_area la,lab_unidad lu,lab_rango lra,lab_tipo_rango ltr where la.codigo=lex.codigoarea_fk and lsa.codigo=lex.codigosubarea_fk and lu.codigo=lra.codunidad_fk and ltr.codigo=lra.codtiporango_fk and lsa.codigo='"+CodSubArea+"' and lex.codigosubarea_fk>0 and la.codigo=0 and lex.codigo=ltr.codexamen_fk and lex.tipo=1 union select lex.codigo,lex.nombre,lex.tipo,replace(lex.codigo,lex.codigo,'') as valInicial,replace(lex.codigo,lex.codigo,'') as valFinal,replace(lex.codigo,lex.codigo,'') as NomUni,lsa.nombre from lab_examen lex,lab_subarea lsa,lab_area la where la.codigo=lex.codigoarea_fk and lsa.codigo=lex.codigosubarea_fk and lsa.codigo='"+CodSubArea+"' and lex.codigosubarea_fk>0 and la.codigo=0 and lex.tipo=0 ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerExamenesArea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerExamenesIndividualTexto(String CodExamen){
		/**
		 * se obtienen los examenes del grupo seleccionado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lex.codigo,lex.nombre,lex.tipo from lab_examen lex,lab_subarea lsa,lab_area la where la.codigo=lex.codigoarea_fk and lsa.codigo=lex.codigosubarea_fk and lex.codigo='"+CodExamen+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerExamenesIndividualTexto "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerExamenesIndividualRango(String CodExamen){
		/**
		 * se obtienen los examenes del grupo seleccionado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lex.codigo,lex.nombre,lex.tipo,lra.valorinicial,lra.valorfinal,lu.nombre from lab_examen lex,lab_subarea lsa,lab_area la,lab_unidad lu,lab_rango lra,lab_tipo_rango ltr where la.codigo=lex.codigoarea_fk and lsa.codigo=lex.codigosubarea_fk and lu.codigo=lra.codunidad_fk and ltr.codigo=lra.codtiporango_fk and lex.codigo='"+CodExamen+"' and lex.codigo=ltr.codexamen_fk and lex.tipo=1 ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerExamenesIndividualRango "+ex);
        }	
        return rs;
    }
	
	public void ActualizarPesoTalla(String CodAdm,String Peso,String Talla){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_peso_talla SET peso='"+Peso+"',talla='"+Talla+"' WHERE CodAdm="+CodAdm+" ");
	     	//System.out.println("UPDATE hic_peso_talla SET peso='"+Peso+"',talla='"+Talla+"' WHERE CodAdm="+CodAdm+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO ActualizarPesoTalla:_ "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarHoraDetalleDosis(String CodHoraDisp,String NuevaHora ){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_horas_dosificacion SET hora_dosis='"+NuevaHora+"' WHERE codigo='"+CodHoraDisp+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO ActualizarHoraDetalleDosis:_ "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarDetalleDosis(String CodUsu,String NomUsu,String FechaAdministra,String HoraAdministra,String Estado,String CodHoraDisp,String Observacion ){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_horas_dosificacion SET CodUsuDosis_FK='"+CodUsu+"',NomUsuDosis='"+NomUsu+"', fecha_administracion='"+FechaAdministra+"', hora_administracion='"+HoraAdministra+"',estado='"+Estado+"',observacion='"+Observacion+"' WHERE codigo='"+CodHoraDisp+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO ActualizarDetalleDosis:_ "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void EstadoDeAsignacion(String CodAsignacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update ord_pac_lab set estado=1 where codigo='"+CodAsignacion+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO EstadoDeAsignacion:_ "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ObtenerNombreUsuario(String Usuario){
		/**
		 * se obtienen los nombre del usuario que hizo la insercion
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select sdp.nombre,sdp.apellido from seg_usuario su,seg_datos_personales sdp where sdp.dat_codigo=su.dat_codigo_fk and su.usu_codigo='"+Usuario+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerExamenesIndividualRango "+ex);
        }	
        return rs;
    }
	
	public void CrearPerfilft(String a,String p,String fi,String fe,String area,String sarea,String c,String cdx, String aler, String sexo, String edad, String peso, String talla){
		/**
		 * creamos la formulacion CE
		 */
	    
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO farc_perfil_farmacoterapeutico(cod_admisionfk,cod_pacientefk,fechai,fechae,cod_areafk,cod_subareafk,cod_camafk,cod_dxfk,alergias,sexo,edad,peso,talla)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, a);
			    ps.setString(2, p);
			    ps.setString(3, fi);
			    ps.setString(4, fe);
			    ps.setString(5, area);
			    ps.setString(6, sarea);
			    ps.setString(7, c);
			    ps.setString(8, cdx);
			    ps.setString(9, aler);
			    ps.setString(10, sexo);
			    ps.setString(11, edad);
			    ps.setString(12, peso);
			    ps.setString(13, talla);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();		
						
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearPerfilft "+ex);
			}
		}
	
	
	public java.sql.ResultSet ObtenerDatosPerfilFarmaco(String CodAdm){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aad.fecha_registro,ap.genero,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)) - (RIGHT(CURDATE(),5) < RIGHT(ap.fecha_nacimiento, 5)) AS edad, " +
        			" acc.pabellon,acc.ubicacion_cama,acc.codigocama,(SELECT GROUP_CONCAT(a.nombre,'-',a.codDiagnostico) " +
        			" FROM(SELECT hi.codDiagnostico,ci.nombre FROM hic_diagnosticoingreso hi,cie10 ci WHERE hi.codAdm="+CodAdm+" AND ci.codigo=hi.CodDiag_fk " +
        			" UNION " +
        			" SELECT he.codDiagnostico,ci.nombre FROM hic_diagnosticoegreso he,cie10 ci WHERE he.codAdm="+CodAdm+" AND ci.codigo=he.CodDiag_fk ) AS a) AS Diagnosticos,acc.servicio " +
        			" FROM adm_admisiones aad,adm_paciente ap,adm_censo_cama acc " +
        			" WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
        			" AND aad.adm_numero_ingreso="+CodAdm+" " +
        			" AND acc.cen_numero_cama=aad.cen_numero_cama_fk " +
        	" AND (acc.servicio=2 OR acc.servicio=3)");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerCodPerfilft "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerFormulacionMedicamento(String CodHoraDispCDO){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_horas_dosificacion hhd WHERE codigo='"+CodHoraDispCDO+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormulacionMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerHoraExisteFormulacionMedicamento(String CodDetFormu,String HoraDosis){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_horas_dosificacion hhd WHERE hhd.CodDetForm_FK='"+CodDetFormu+"' AND hhd.hora_dosis='"+HoraDosis+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormulacionMedicamento "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerNombreUsuarioK(String CodUsu){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido,' (',sdt.profesion,' ',sdt.ocupacion,')') AS Usuario FROM seg_usuario su,seg_datos_personales sdt WHERE sdt.dat_codigo=su.dat_codigo_fk AND su.usu_codigo="+CodUsu+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPesoTalla "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerPesoTalla(String CodAdm ){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" ");
        	//System.out.println("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPesoTalla "+ex);
        }	
        
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPesoTallaTodosDatosADMFECH(String CodAdm,String Fecha ){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" and fecha='"+Fecha+"' ");
        	System.out.println("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" and fecha='"+Fecha+"' ");
        	//System.out.println("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPesoTallaTodosDatos "+ex);
        }	
        
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPesoTallaTodosDatos(String CodAdm,String Fecha,String Hora ){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" and fecha='"+Fecha+"' and hora='"+Hora+"' ");
        	//System.out.println("SELECT * FROM hic_peso_talla WHERE CodAdm="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPesoTallaTodosDatos "+ex);
        }	
        
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerAlergiasPeso(String CodForm,String CodAdm ){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT GROUP_CONCAT(resultados) datos FROM hic_resultado WHERE CodFormato_fk="+CodForm+" AND codAdm_fk="+CodAdm+" AND (codPreg_fk='9' OR codPreg_fk='75') ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPesoAlergias "+ex);
        }	
        return rs;
    }
	
	 
	public java.sql.ResultSet SoloMedicamento(String codMed){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM medicamentos WHERE cod_grupoFK=1 AND codigo="+codMed+"");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo SoloMedicamento "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerCodPerfilft(String adm){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM farc_perfil_farmacoterapeutico WHERE cod_admisionfk='"+adm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerCodPerfilft "+ex);
        }	
        return rs;
    }
	
	
	public void CrearDetallePerfilft(String cp,String cm,String cmed,String via,String f,String c,String fec, String hra,String formula){
		/**
		 * creamos la formulacion CE
		 */
	    
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO farc_detalle_perfil_farmacoterapeutico(cod_perfilfk,cod_medicamentofk,cod_medico,via_admon,frecuencia,cantidad,fecha,hora,cod_dformulacion)VALUES(?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, cp);
			    ps.setString(2, cm);
			    ps.setString(3, cmed);
			    ps.setString(4, via);
			    ps.setString(5, f);
			    ps.setString(6, c);
			    ps.setString(7, fec);
			    ps.setString(8, hra);
			    ps.setString(9, formula);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();		
						 
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearDetallePerfilft "+ex);
			}
		}
	public void CrearPesoTallaCompleto(String CodAdm,String Peso,String Talla,String ta,String fc,
			String fr,String pulso,String fecha,String hora,String fecha_insercion,
			String hora_insercion,String CodUsu_fk){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_peso_talla(CodAdm,peso,talla,ta,fc,fr,pulso,fecha,hora,fecha_insercion,hora_insercion,CodUsu_fk)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, CodAdm);
			    ps.setString(2, Peso);
			    ps.setString(3, Talla);
			    ps.setString(4, ta);
			    ps.setString(5, fc);
			    ps.setString(6, fr);
			    ps.setString(7, pulso);
			    ps.setString(8, fecha);
			    ps.setString(9, hora);
			    ps.setString(10, fecha_insercion);
			    ps.setString(11, hora_insercion);
			    ps.setString(12, CodUsu_fk);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();						
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearPesoTallaCompleto "+ex);
			}
		}
	
	public void CrearPesoTalla(String CodAdm,String Peso,String Talla){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_peso_talla(CodAdm,peso,talla)VALUES(?,?,?)");
			    ps.setString(1, CodAdm);
			    ps.setString(2, Peso);
			    ps.setString(3, Talla);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();						
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearPesoTalla "+ex);
			}
		}
	
	
	public void CrearOrdenProduccion(String Fecha,String Hora){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO farc_orden_produccion (fecha,hora)VALUES(?,?)");
			    ps.setString(1, Fecha);
			    ps.setString(2, Hora);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();						
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearOrdenProduccion "+ex);
			}
		}
	
	public void CrearDetalleOrdenProduccion(String cod_ordenproduccionfk,String cod_medOrden_Produccionfk,String cod_formulafk,String cod_detalleformulafk,String cod_pacientefk,String subarea,String cama,String dosis,String frecuencia,String unidad ){
		System.out.print("String cod_ordenproduccionfk "+ cod_ordenproduccionfk+" String cod_medOrden_Produccionfk "+cod_medOrden_Produccionfk+" String cod_formulafk "+cod_formulafk+" String cod_detalleformulafk "+cod_detalleformulafk+" String cod_pacientefk "+cod_pacientefk+" String subarea "+subarea+" String cama "+cama+" String dosis "+dosis+" String frecuencia "+frecuencia+" String unidad "+unidad);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO farc_detalle_orden_produccion (cod_ordenproduccionfk,cod_medOrden_Produccionfk,cod_formulafk,cod_detalleformulafk,cod_pacientefk,subarea,cama,dosis,frecuencia,unidad)VALUES(?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1, cod_ordenproduccionfk);
			    ps.setString(2, cod_medOrden_Produccionfk);
			    ps.setString(3, cod_formulafk);
			    ps.setString(4, cod_detalleformulafk);
			    ps.setString(5, cod_pacientefk);
			    ps.setString(6, subarea);
			    ps.setString(7, cama);
			    ps.setString(8, dosis);
			    ps.setString(9, frecuencia);
			    ps.setString(10, unidad);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();						
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoVerFormatos>>CrearDetalleOrdenProduccion "+ex);
			}
		}
	
	public java.sql.ResultSet ObtenerMedicamentoParaOrden(String cod_medfk){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_med_orden_produccion WHERE cod_medfk="+cod_medfk+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerMedicamentoParaOrden "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerOrdenProduccion(String Fecha){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_orden_produccion WHERE fecha='"+Fecha+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerOrdenProduccion "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerCorteProduccion(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_corte_produccion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ObtenerCorteProduccion "+ex);
        }	
        return rs;
    }
	
	
	
	
	public java.sql.ResultSet ListarUnidades(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM farc_udpresentacion ORDER BY sigla ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>ListarUnidades "+ex);
        }	
        return rs;
    }
	
	
	//#################
	public java.sql.ResultSet BuscarInterconsultasPendPac(String codAdm){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT inter.id_interconsulta, inter.motivo, inter.adm_pac, CONCAT(seg.nombre,' ',seg.apellido) AS medico, esp.nombre_especialidad, CONCAT(inter.fecha,' ',inter.hora) AS fecha, inter.cod_pac,inter.cod_especialidad FROM interconsulta AS inter, seg_datos_personales AS seg, agm_especialidad AS esp, seg_usuario usu WHERE inter.adm_pac ='"+codAdm+"' AND inter.estado ='-1' AND inter.cod_usuario = usu.usu_codigo  AND usu.dat_codigo_fk=seg.dat_codigo AND inter.cod_especialidad=esp.codigo");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarInterconsultasPendPac "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet BuscarEspecialidadUsuario(String codUsu){
		java.sql.ResultSet rs=null;
		java.sql.ResultSet rs1=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        		rs=st.executeQuery("SELECT med.codEspe_fk FROM seg_datos_personales AS sdp, agm_medico AS med, seg_usuario usu  WHERE usu.usu_codigo='"+codUsu+"' AND sdp.dat_codigo=usu.dat_codigo_fk AND sdp.numeroDocumento=med.numeroDocumento");
	            	
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarEspecialidadUsuario "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet BuscarNombreUsuario(String codUsuario){	
		/**
		 * consulta que busca nombre del usuario
		 */
        java.sql.ResultSet rs=null;
        java.sql.ResultSet rs1=null;
        Statement st = null;
        //Statement st1 = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT dat_codigo_fk FROM seg_usuario WHERE usu_codigo='"+codUsuario+"'");
        	if(rs.next()){
        	rs1=st.executeQuery("SELECT nombre, apellido FROM seg_datos_personales WHERE dat_codigo='"+rs.getString(1)+"'");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarNombreUsuario "+ex);
        }	
        return rs1;
    }
	
	
	public java.sql.ResultSet BuscarPaciente(String codPac){	
		/**
		 * 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido, aen.nombre_entidad,apac.genero, apac.fecha_nacimiento,fmt.descripcion,fmt.cod_manual_tarifario,aen.ent_nit,aen.ent_nit_contratante,(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento, 5)) AS edad  FROM adm_paciente apac,adm_convenio aco,adm_entidad aen, fact_convenios fc,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt"+
" WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato "+
 "AND aco.ent_nit_contratante_fk = aen.ent_nit AND apac.pac_codigo_paciente='"+codPac+"' AND fc.cod_entidad=aen.ent_nit "+
 "AND ftc.cod_convenio=fc.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario "+
 "AND (fmt.manual_base=1 OR fmt.manual_base=2)");
        	//System.out.println("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido, aen.nombre_entidad,apac.genero, apac.fecha_nacimiento,fmt.descripcion,fmt.cod_manual_tarifario,aen.ent_nit,aen.ent_nit_contratante FROM adm_paciente apac,adm_convenio aco,adm_entidad aen, fact_convenios fc,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato  AND aco.ent_nit_contratante_fk = aen.ent_nit AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"' AND fc.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fc.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND (fmt.manual_base=1 OR fmt.manual_base=2)");
        	//System.out.println("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,aen.nombre_entidad,apac.genero,apac.fecha_nacimiento FROM adm_paciente apac,adm_convenio aco,adm_entidad aen WHERE apac.conv_numero_contrato_fk=aco.conv_numero_contrato AND aco.ent_nit_contratante_fk=aen.ent_nit AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarPaciente "+ex);
        }	
        return rs;
    }
	
	
	public void InsertarInterconsulta(
			String codAdm,String motivo,String cod_usuario,String cod_pac,String cod_esp,Date fecha, Time hora, String estado ){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into interconsulta" +
	    	 		"(motivo,adm_pac,cod_usuario,cod_pac,cod_especialidad,fecha,hora,estado) " +
	    	 		"values(?,?,?,?,?,?,?,?)");	
	    	 
  			psc.setString(1,motivo);
  			psc.setString(2,codAdm);
  			psc.setString(3,cod_usuario);
  			psc.setString(4,cod_pac);
  			psc.setString(5,cod_esp);
  			psc.setDate(6,fecha);
  			psc.setTime(7,hora);
  			psc.setString(8,estado);
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>InsertarInterconsulta "+ex);
	        }	
	 }
	
	public void InsertarMovInterconsulta(
			Date fecha,
			Time hora,
			String codProg,
			String codRef,
			String nombreProg,
			String valor,
			String codUsu,
			String codEncFact,
			String codMed,
			String subcentroCosto){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into fact_det_factura" +
	    	 		"(fecha,hora,tipopop,cod_programafk,cod_programa,nombre_programa,clase_servicio,fecha_realizacion,cantidad," +
	    	 		"valor,cod_usuario,cod_enc_factura_fk,cod_medico,subcentrodecosto,formaactoqx) " +
	    	 		"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
	    	 
	        psc.setDate(1,fecha);
  			psc.setTime(2,hora);
  			psc.setString(3,"1");
  			psc.setString(4,codProg);
  			psc.setString(5,codRef);
  			psc.setString(6,nombreProg);
  			psc.setString(7,"CONSULTA");
  			psc.setDate(8,fecha);
  			psc.setString(9,"1");
  			psc.setString(10,valor);
  			psc.setString(11,codUsu);
  			psc.setString(12,codEncFact);
  			psc.setString(13,codMed);
  			psc.setString(14,subcentroCosto);
  			psc.setString(15,"Seleccione");
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>InsertarMovInterconsulta "+ex);
	        }	
	 }
	
	
	public void InsertarSolicitudTraslado(
			Date fecha, Time hora, String cama_act, String servicio_trasladar, String motivo, String codpac, String codadm, String codusuario, String estado ){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into hic_solicitar_traslado" +
	    	 		"(fecha,hora,cama_act,servicio_trasladar,motivo_traslado,codpac,codadm,codusuario,estado) " +
	    	 		"values(?,?,?,?,?,?,?,?,?)");	
	    	 
  			psc.setDate(1,fecha);
  			psc.setTime(2,hora);
  			psc.setString(3,cama_act);
  			psc.setString(4,servicio_trasladar);
  			psc.setString(5,motivo);
  			psc.setString(6,codpac);
  			psc.setString(7,codadm);
  			psc.setString(8,codusuario);
  			psc.setString(9,estado);
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>InsertarSolicitudTraslado "+ex);
	        }	
	 }
	
	
	public java.sql.ResultSet BuscarInterconsultas(String admPac, String codEsp, String estado){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT inter.id_interconsulta, inter.motivo, inter.adm_pac, esp.nombre_especialidad " +
	        			"FROM interconsulta AS inter, agm_especialidad AS esp  " +
	        			"WHERE inter.adm_pac='"+admPac+"' AND inter.cod_especialidad='"+codEsp+"' AND inter.estado='"+estado+"' AND inter.cod_especialidad=esp.codigo");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarInterconsultas "+ex);
	        }	
	        return rs;	
	 }
	
	
	public java.sql.ResultSet BuscarTraslado(String admPac, String estado){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM hic_solicitar_traslado WHERE codadm='"+admPac+"' AND estado='"+estado+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarTraslado "+ex);
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
	
	
	public void modificarInterconsulta(
			String motivo,
			String idInter
			) {
				
			 PreparedStatement psc = null;
					try {
						Conexion con = new Conexion();
						psc = con.conn.prepareStatement(
								"UPDATE interconsulta SET motivo = ? WHERE id_interconsulta='"+idInter+"'");
						psc.setString(1, motivo);
						psc.executeUpdate();
						psc.close();
						con.cerrar();
						
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						System.out.println("Error en MetodoVerFormatos>>modificarInterconsulta "	+ ex);
						
					}
				
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
        	rs=st.executeQuery("select codigo,nombre_especialidad from agm_especialidad WHERE estado='0' "+Validacion+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarEspecialidadTodas "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarEspecialidadTodas2(){	
		/**
		 * consulta que busca todas las especialidades
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_especialidad from agm_especialidad WHERE estado='0' order by nombre_especialidad ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoVerFormatos>>BuscarEspecialidadTodas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarInterconsultasPend(String codUsu, String admPac){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM interconsulta" +
	        			" WHERE adm_pac ='"+admPac+"' " +
	        					"AND cod_especialidad =(SELECT med.codEspe_fk FROM seg_datos_personales AS sdp, agm_medico AS med  WHERE sdp.dat_codigo='"+codUsu+"' AND sdp.numeroDocumento=med.numeroDocumento) AND estado='-1'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarInterconsultasPend "+ex);
	        }	
	        return rs;	
	 }
	
	public java.sql.ResultSet BuscarFormInter(String idFormato){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM hic_adm_formatos_pac WHERE codigo_for_fk= (SELECT codigo FROM hic_formato WHERE nombre_formato='interconsulta') AND codigo='"+idFormato+"';");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarFormInter "+ex);
	        }	
	        return rs;	
	 }
	
	
	public void ActualizarEstadoInterconsultas(String CodMedEsp,String codFormato, String admPac){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE interconsulta " +
	     			"SET estado='1', cod_especialista_realiza='"+CodMedEsp+"', cod_formato='"+codFormato+"'  " +
	     			"WHERE adm_pac ='"+admPac+"' AND cod_especialidad =(SELECT med.codEspe_fk  FROM seg_datos_personales AS sdp, agm_medico AS med, seg_usuario seg  WHERE seg.usu_codigo='"+CodMedEsp+"' AND seg.dat_codigo_fk = sdp.dat_codigo AND sdp.numeroDocumento=med.numeroDocumento) AND estado='-1' ");
	     	
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoVerFormatos>>ActualizarEstadoInterconsultas "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public java.sql.ResultSet BuscarDatosPac(String codAdm, String codUsu){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT admpac.tipo_documento AS TipoDocPac, admpac.numero_documento AS NumDocPac," +
	        			" usu.dat_codigo_fk AS codMedico, fact.cod_enc_factura FROM adm_paciente admpac, " +
	        			"adm_admisiones adm, seg_usuario usu, fact_enc_factura fact " +
	        			"WHERE adm.adm_numero_ingreso='"+codAdm+"' AND adm.pac_codigo_paciente_fk=admpac.pac_codigo_paciente" +
	        			" AND usu.usu_codigo='"+codUsu+"' AND fact.cod_admision=adm.adm_numero_ingreso");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarDatosPac "+ex);
	        }	
	        return rs;	
	 }
	
	
	public java.sql.ResultSet BuscarManualTarifario(String tipoDoc, String numDoc){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido, aen.nombre_entidad,apac.genero, "+
                                   "apac.fecha_nacimiento,fmt.descripcion,fmt.cod_manual_tarifario,aen.ent_nit,aen.ent_nit_contratante, "+
                                   "(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento, 5)) AS edad,fmt.manual_base "+  
                                   "FROM adm_paciente apac,adm_convenio aco,adm_entidad aen, fact_convenios fc,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt "+
                                   "WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato  AND aco.ent_nit_contratante_fk = aen.ent_nit "+ 
                                   "AND apac.tipo_documento='"+tipoDoc+"' AND apac.numero_documento='"+numDoc+"' AND fc.cod_entidad=aen.ent_nit "+
                                   "AND ftc.cod_convenio=fc.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND (fmt.manual_base=1 OR fmt.manual_base=2)");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarManualTarifario "+ex);
	        }	
	        return rs;	
	 }
	
	
	public java.sql.ResultSet BuscarValorInterconsulta(String codManualTarifario, String codRef){
		java.sql.ResultSet rs=null;
		 Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM fact_tarifas ft, fact_programas fp " +
	        			"WHERE ft.manual_tarifario='"+codManualTarifario+"' AND fp.cod_programa=ft.programa AND fp.cod_referencia='"+codRef+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoVerFormatos>>BuscarValorInterconsulta "+ex);
	        }	
	        return rs;	
	 }
	
	
	//###############
	
	
	
}
