/**
 * logica: MetodoCrearFormato se encuentran las inserciones,consultas y actualizaciones
 * para el manejo de la asignacion de las preguntas y tipos de respuestas.
*/
package pat_metodo;


import pat_bean.CrearFormato;
import pat_bean.CrearFormatoHic;
import pat_bean.IngresarResultados;
import pat_bean.PreguntaRespuesta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoCrearFormato {
	
	public void ActualizarResultados(String Resul, String CodResul){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update pat_resultado set resultados='"+Resul+"' where codigo='"+CodResul+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearFormato>>ActualizarResultados "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarDatosComplementarios(String FechaRecibo, String FechaEntrega,String Tipoespecimen,String DiagClinico,String MedicoTratante,String Protocolo,String CodDatosComplementarios ){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update pat_datoscomplementarios set fecharecibo='"+FechaRecibo+"',fechaentrega='"+FechaEntrega+"',tipoespecimen='"+Tipoespecimen+"',diagnosticoclinico='"+DiagClinico+"',medicotratante='"+MedicoTratante+"',protocolo='"+Protocolo+"' where codigo='"+CodDatosComplementarios+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoCrearFormato>>ActualizarDatosComplementarios "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	public void IngresarHistoria(String codPa_fk,String codPreg_fk,String resultados,String estado,String fecha,String hora,String codUsu_fk,String codArea_Fk,String CodFormato){
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
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_resultado(codPac_fk,codPreg_fk,resultados,estado,fecha,hora,codUsu_Fk,codArea_Fk,CodFormato_fk)values(?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getcodPa_fk());
				    ps.setString(2,ir.getcodPreg_fk());
				    ps.setString(3,ir.getresultados());
				    ps.setString(4,ir.getestado());
				    ps.setString(5,ir.getFecha());
				    ps.setString(6,ir.getHora());
				    ps.setString(7, ir.getcodUsu_fk());
				    ps.setString(8, ir.getcodArea_Fk());
				    ps.setString(9, ir.getCodFormato());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en MetodoCrearFormato>>IngresarHistoria "+ex);
				}
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
        	rs=st.executeQuery("select hr.nombre_respuesta,hr.codigo as CodRespuesta from pat_tipo_respuesta htr,pat_condicion_respuesta hcr,hic_respuestas hr where htr.codigo=hcr.codigo_tiporespuesta_fk and hr.codigo=hcr.codigo_respuesta_fk and htr.codigo='"+CodTipoResp+"' order by hr.nombre_respuesta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>OpcionesTipoRespuesta2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPreguntasAreaLlenoBR(String FechaFormato,String HoraFormato,String CodPaciente,String CodFormato){	   
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT ppac.tipo_documento,ppac.numero_documento,ppac.nombre,CONCAT(ppac.primer_apellido,' ',ppac.segundo_apellido) AS apellido FROM pat_adm_formatos_pac hfa,adm_paciente ppac WHERE ppac.pac_codigo_paciente = hfa.codigo_pac_fk  AND hfa.fecha = '"+FechaFormato+"' AND hfa.hora = '"+HoraFormato+"' AND hfa.codigo_for_fk = "+CodFormato+" ");
        	System.out.println("SELECT DISTINCT ppac.tipo_documento,ppac.numero_documento,ppac.nombre,CONCAT(ppac.primer_apellido,' ',ppac.segundo_apellido) AS apellido FROM pat_adm_formatos_pac hfa,adm_paciente ppac WHERE ppac.pac_codigo_paciente = hfa.codigo_pac_fk  AND hfa.fecha = '"+FechaFormato+"' AND hfa.hora = '"+HoraFormato+"' AND hfa.codigo_for_fk = "+CodFormato+" ");
        	//rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad,ppac.tipo_documento,ppac.identificacion,ppac.nombre,ppac.apellidos,hfor.nombre_formato from pat_area ha,pat_area_pregunta hap,pat_pregunta hp,pat_pregunta_tiporespuesta hpt,pat_tipo_respuesta htr,pat_formato hfor,pat_formato_area hfa,pat_resultado hres,adm_paciente ppac where ha.codigo=hap.codigo_area_fk and ppac.codigo=hres.codPac_fk and hres.codPac_fk="+CodPaciente+" and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hfor.codigo="+CodFormato+" ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerPreguntasAreaLleno "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPreguntasAreaLleno(String FechaFormato,String HoraFormato,String CodPaciente,String CodFormato){	   
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad,ppac.tipo_documento,ppac.numero_documento,ppac.nombre,CONCAT(ppac.primer_apellido,' ',ppac.segundo_apellido)as apellido,hfor.nombre_formato from pat_area ha,pat_area_pregunta hap,pat_pregunta hp,pat_pregunta_tiporespuesta hpt,pat_tipo_respuesta htr,pat_formato hfor,pat_formato_area hfa,pat_resultado hres,adm_paciente ppac where ha.codigo=hap.codigo_area_fk and ppac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPaciente+" and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hfor.codigo="+CodFormato+" ");
        	//System.out.println("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad,ppac.tipo_documento,ppac.numero_documento,ppac.nombre,CONCAT(ppac.primer_apellido,' ',ppac.segundo_apellido)as apellido,hfor.nombre_formato from pat_area ha,pat_area_pregunta hap,pat_pregunta hp,pat_pregunta_tiporespuesta hpt,pat_tipo_respuesta htr,pat_formato hfor,pat_formato_area hfa,pat_resultado hres,adm_paciente ppac where ha.codigo=hap.codigo_area_fk and ppac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPaciente+" and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hfor.codigo="+CodFormato+" ");
        	//rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad,ppac.tipo_documento,ppac.identificacion,ppac.nombre,ppac.apellidos,hfor.nombre_formato from pat_area ha,pat_area_pregunta hap,pat_pregunta hp,pat_pregunta_tiporespuesta hpt,pat_tipo_respuesta htr,pat_formato hfor,pat_formato_area hfa,pat_resultado hres,adm_paciente ppac where ha.codigo=hap.codigo_area_fk and ppac.codigo=hres.codPac_fk and hres.codPac_fk="+CodPaciente+" and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hfor.codigo="+CodFormato+" ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerPreguntasAreaLleno "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosPaciente(){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,hafp.codigo AS CodigoAsignacion,ppac.nombre AS NomPac,ppac.primer_apellido AS ApePac,hf.repetido FROM pat_formato hf,pat_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp,adm_paciente ppac WHERE hf.codigo=hafp.codigo_for_fk AND ppac.pac_codigo_paciente =hafp.codigo_pac_fk AND hafp.codigo_usu_fk=sus.usu_codigo AND sdp.dat_codigo=sus.dat_codigo_fk AND hafp.estado=0 ORDER BY hafp.fecha DESC ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerFormatosPaciente "+ex);
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
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hp.codigo as CodPregunta,ha.codigo as CodArea,hp.patronNormal from pat_area ha,pat_area_pregunta hap,pat_pregunta hp,pat_pregunta_tiporespuesta hpt,pat_tipo_respuesta htr,pat_formato hfor,pat_formato_area hfa where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerPreguntasArea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerAreaFormato(String CodFormato,String Fecha,String Hora){
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
        	rs=st.executeQuery("select ha.codigo,ha.nombre_area,hafp.hora,hafp.fecha from pat_formato hf,pat_formato_area hfa,pat_area ha,pat_adm_formatos_pac hafp where hf.codigo=hfa.codigo_formato_fk and ha.codigo=hfa.codigo_area_fk and hf.codigo=hafp.codigo_for_fk and hf.codigo='"+CodFormato+"' and hafp.fecha='"+Fecha+"' and hafp.hora='"+Hora+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerAreaFormato "+ex);
        }	
        return rs;
    }
	
	public void RelacionFormatoAdmisionPaciente(String CodFormato,String CodPaciente,String Hora,String Fecha,String CodUsuario){
		/**
		 * creamos la relacion de los formatos con la admision y el paciente 
		 * lleva como parametro el codigo del formato,codigo de la admision,codigo del paciente
		 */
	   CrearFormatoHic cfa = new CrearFormatoHic();
	   cfa.setcodigo_pac_fk(CodPaciente);
	   cfa.setcodigo_formato_fk(CodFormato);
	   cfa.setCodUsuario(CodUsuario);
	   cfa.setHora(Hora);
	   cfa.setFecha(Fecha);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pat_adm_formatos_pac(codigo_pac_fk,codigo_for_fk,fecha,hora,codigo_usu_fk)values(?,?,?,?,?)");
			    ps.setString(1, cfa.getcodigo_pac_fk());
			    ps.setString(2, cfa.getcodigo_formato_fk());
			    ps.setString(3, cfa.getFecha());
			    ps.setString(4, cfa.getHora());
			    ps.setString(5, cfa.getCodUsuario());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearFormato>>RelacionFormatoAdmisionPaciente "+ex);
			}

		}

	public java.sql.ResultSet ObtenerFormatosHospitalizacion(String NumDocumento,String TipoDoc){
		/**
		*Se obtienes el historial de formatos que se le han hecho al paciente
		*mientras estuvo en hospitalizacion
		*
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.tipo_documento,apac.numero_documento from pat_formato hf,adm_admisiones adm,pat_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp,adm_paciente apac where hf.codigo=hafp.codigo_for_fk and adm.adm_numero_ingreso=hafp.codigo_adm_fk and adm.pac_codigo_paciente_fk =hafp.codigo_pac_fk and hafp.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk and apac.tipo_documento='"+TipoDoc+"' and apac.numero_documento='"+NumDocumento+"' and apac.pac_codigo_paciente=hafp.codigo_pac_fk and hafp.estado=1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerFormatosHospitalizacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosPreingreso(String NumDocumento,String TipoDoc){
		/**
		*Se obtienes el historial de formatos que se le han hecho al paciente
		*mientras estuvo en la preadmision
		*
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hf.codigo as CodFormato,hf.nombre_formato,afc.fecha,afc.hora,sdp.nombre as NomUsuario,sdp.apellido as ApeUsuario,afc.codigo_usu_fk,afc.cedulaPac,ac.nombre,ac.primer_apellido,ac.segundo_apellido,ac.tipo_documento,ac.numero_documento from pat_formato hf,adm_cola ac,adm_formatoscola afc,seg_usuario sus,seg_datos_personales sdp where hf.codigo=afc.codigo_for_fk and ac.col_codigo=afc.codigo_cola_fk and ac.numero_documento =afc.cedulaPac and ac.numero_documento='"+NumDocumento+"' and ac.tipo_documento='"+TipoDoc+"' and afc.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk and afc.estado=1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerFormatosPreingreso "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoPregunta(String pregunta){
		/**
		 * se busca si ya existe una tipo de respuesta con el mismo nombre
		 * lleva como parametro el nombre del tipo de respuesta
		 * 
		 * y como respuesta envia el codigo,el nombre del tipo de respuesta y
		 * el tipo de respuesta si es abierta(1) o cerrada(2)
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_tiporespuesta,tipo from pat_tipo_respuesta where nombre_tiporespuesta='"+pregunta+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerCodigoPregunta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoRespuesta(String respuesta){	
		/**
		 * se obtiene el codigo de la respuesta ingresada con el fin de usarlos
		 * posteriormente,lleva como parametro el nombre de la respuesta.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_respuesta from pat_respuestas where nombre_respuesta='"+respuesta+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>ObtenerCodigoRespuesta "+ex);
        }	
        return rs;
    }
	
	public void CrearPregunta(String pregunta,String tipo){
		/**
		 * se crea el tipo de respuesta tiene como parametro el nombre del tipo de respuesta
		 * y el tipo si es abierta(1) si es cerrada(2)
		 */
	    CrearFormato cf = new CrearFormato();
	    cf.setNombrePregunta(pregunta);
	    cf.setTipoPregunta(tipo);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pat_tipo_respuesta(nombre_tiporespuesta,tipo)values(?,?)");
			    ps.setString(1, cf.getNombrePregunta());
			    ps.setString(2, cf.getTipoPregunta());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR MetodoCrearFormato>>CrearPregunta "+ex);
			}
		}
	
	public void CrearRespuesta(String respuesta){
		/**
		 * se crean las respuestas lleva como parametro el nombre de la respuesta.
		 */
	    CrearFormato cf = new CrearFormato();
	    cf.setNombreRespuesta(respuesta);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pat_respuestas(nombre_respuesta)values(?)");
			    ps.setString(1, cf.getNombreRespuesta());
			   	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearFormato>>CrearRespuesta "+ex);
			}
		}
	
	public void RelacionPreguntaRespuesta(String codigo_item_formato_fk,String codigo_respuesta_fk){
		/**
		 * se crea la relacion de la pregunta con la respuesta con el fin de unirlas
		 * lleva como parametro el codigo de la pregunta y el codigo de la respuesta.
		 */
	    PreguntaRespuesta pr = new PreguntaRespuesta();
	    pr.setcodigo_item_formato_fk(codigo_item_formato_fk);
	    pr.setcodigo_respuesta_fk(codigo_respuesta_fk);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pat_condicion_respuesta(codigo_tiporespuesta_fk,codigo_respuesta_fk)values(?,?)");
			    ps.setString(1,pr.getcodigo_item_formato_fk());
			    ps.setString(2, pr.getcodigo_respuesta_fk());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearFormato>>RelacionPreguntaRespuesta "+ex);
			}
		}
	
}
