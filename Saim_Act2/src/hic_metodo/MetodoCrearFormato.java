/**
 * logica: MetodoCrearFormato se encuentran las inserciones,consultas y actualizaciones
 * para el manejo de la asignacion de las preguntas y tipos de respuestas.
*/
package hic_metodo;


import hic_bean.CrearFormato;
import hic_bean.PreguntaRespuesta;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoCrearFormato {
	
	public java.sql.ResultSet ObtenerOrdenesMedicas(String NumDocumento,String TipoDoc){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('1')AS Tipo FROM hic_orden hod,hic_detalleordenlaboratorio hdl,seg_usuario su,seg_datos_personales sdt,adm_paciente apac WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdl.CodOrdLab_fk AND hod.TipoOrden=1 AND apac.tipo_documento='"+TipoDoc+"' AND apac.numero_documento='"+NumDocumento+"' AND apac.pac_codigo_paciente=hod.CodPac_fk UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('2')AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt,adm_paciente apac WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdi.CodOrdImg_fk AND hod.TipoOrden=2 AND apac.tipo_documento='"+TipoDoc+"' AND apac.numero_documento='"+NumDocumento+"' AND apac.pac_codigo_paciente=hod.CodPac_fk UNION SELECT DISTINCT hfor.codigo,CONCAT(hfor.fecha,'/',hfor.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('3')AS Tipo FROM hic_formulacion hfor,hic_detalle_formulacion hdf,seg_usuario su,seg_datos_personales sdt,adm_paciente apac WHERE hfor.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hfor.codigo=hdf.CodFormulacion_fk AND apac.tipo_documento='"+TipoDoc+"' AND apac.numero_documento='"+NumDocumento+"' AND apac.pac_codigo_paciente=hfor.CodPac_fk UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('4')AS Tipo FROM hic_orden hod,seg_usuario su,seg_datos_personales sdt,adm_paciente apac WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.TipoOrden=4 AND apac.tipo_documento='"+TipoDoc+"' AND apac.numero_documento='"+NumDocumento+"' AND apac.pac_codigo_paciente=hod.CodPac_fk UNION  SELECT DISTINCT  hod.codigo, CONCAT(hod.fecha, '/', hod.hora) AS Fecha,CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario, CONCAT(sdt.profesion, ' ', sdt.ocupacion) AS profesion, CONCAT('5') AS Tipo  FROM  hic_orden hod,seg_usuario su,seg_datos_personales sdt,adm_paciente apac WHERE hod.CodUsu_fk = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo  AND hod.TipoOrden = 5 AND apac.tipo_documento='"+TipoDoc+"' AND apac.numero_documento='"+NumDocumento+"' AND apac.pac_codigo_paciente = hod.CodPac_fk   UNION SELECT DISTINCT ordenpat.idOrdenPat, CONCAT( ordenpat.fecha, '/', ordenpat.hora ) AS Fecha, CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario, CONCAT(sdt.profesion, ' ', sdt.ocupacion) AS profesion, CONCAT('6') AS Tipo FROM ordenpat_encabezado ordenpat, seg_usuario su, seg_datos_personales sdt, adm_paciente apac WHERE ordenpat.codusuario = su.usu_codigo  AND su.dat_codigo_fk = sdt.dat_codigo  AND apac.tipo_documento = '"+TipoDoc+"' AND apac.numero_documento = '"+NumDocumento+"' AND apac.pac_codigo_paciente = ordenpat.codpaciente  AND ordenpat.estado != 0  ) AS Consulta ORDER BY Consulta.Fecha DESC");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerOrdenesMedicas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosCexterna(String NumDocumento,String TipoDoc){
		/**
		*Se obtienes el historial de formatos que se le han hecho al paciente
		*mientras estuvo en consulta externa
		*
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT afp.fecha,afp.hora,afp.cod_for_fk,afp.cod_usu_fk,afp.cod_pac_fk,hfor.nombre_formato,CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido) AS Paciente,CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario FROM agm_formatos_pac afp,hic_formato hfor,adm_paciente pac,seg_usuario su,seg_datos_personales sdt WHERE afp.cod_for_fk=hfor.codigo AND pac.pac_codigo_paciente=afp.cod_pac_fk AND afp.cod_usu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND afp.estado=1 AND pac.tipo_documento='"+TipoDoc+"' AND pac.numero_documento='"+NumDocumento+"'  ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormatosHospitalizacion "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerEpicrisis(String NumDocumento,String TipoDoc){
		/**
		*Se obtienes el historial de epicrisis que se le han hecho al paciente
		*
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hep.codigo,hep.servicio,CONCAT(hep.fecha,'/',hep.hora) fecha,CONCAT(sdp.nombre,' ',sdp.apellido) usuario FROM hic_epicrisis hep,adm_admisiones adm,seg_usuario sus,seg_datos_personales sdp,adm_paciente apac WHERE adm.adm_numero_ingreso = hep.codadm AND adm.pac_codigo_paciente_fk = hep.codpac  AND hep.codusu = sus.usu_codigo AND sdp.dat_codigo = sus.dat_codigo_fk  AND apac.tipo_documento = '"+TipoDoc+"'  AND apac.numero_documento = '"+NumDocumento+"'  AND apac.pac_codigo_paciente = hep.codpac ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerEpicrisis "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerDescripcion(String NumDocumento,String TipoDoc){
		/**
		*Se obtienes el historial de epicrisis que se le han hecho al paciente
		*
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT d.codigo,d.fecha,d.horai,p.apellido,p.nombre,d.cod_pacientefk FROM hic_descripcionqx d, seg_usuario u, seg_datos_personales p, adm_paciente pa WHERE d.estado='1' AND d.cod_usuariofk=u.usu_codigo AND u.dat_codigo_fk=p.dat_codigo AND d.cod_pacientefk=pa.pac_codigo_paciente AND pa.tipo_documento='"+TipoDoc+"' AND pa.numero_documento='"+NumDocumento+"'   ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerDescripcion "+ex);
        }	
        return rs;
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
        	rs=st.executeQuery("select hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.tipo_documento,apac.numero_documento,hf.repetido,hafp.codigo_adm_fk  from hic_formato hf,adm_admisiones adm,hic_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp,adm_paciente apac where hf.codigo=hafp.codigo_for_fk and adm.adm_numero_ingreso=hafp.codigo_adm_fk and adm.pac_codigo_paciente_fk =hafp.codigo_pac_fk and hafp.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk and apac.tipo_documento='"+TipoDoc+"' and apac.numero_documento='"+NumDocumento+"' and apac.pac_codigo_paciente=hafp.codigo_pac_fk and hafp.estado=1");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormatosHospitalizacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DatosPaciente(String NumDocumento,String TipoDoc){
		/**
		*
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as NombrePaciente,CONCAT(apac.tipo_documento,'-',apac.numero_documento)as Identificacion,apac.fecha_nacimiento,(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento)) - (RIGHT(CURDATE(),5) < RIGHT(apac.fecha_nacimiento, 5)) AS edad, aent.nombre_entidad,apac.pac_codigo_paciente from adm_paciente apac,adm_convenio acv,adm_entidad aent where apac.tipo_documento='"+TipoDoc+"' and apac.numero_documento='"+NumDocumento+"' and aent.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearFormato>>DatosPaciente "+ex);
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
        	rs=st.executeQuery("select hf.codigo as CodFormato,hf.nombre_formato,afc.fecha,afc.hora,sdp.nombre as NomUsuario,sdp.apellido as ApeUsuario,afc.codigo_usu_fk,afc.cedulaPac,ac.nombre,ac.primer_apellido,ac.segundo_apellido,ac.tipo_documento,ac.numero_documento from hic_formato hf,adm_cola ac,adm_formatoscola afc,seg_usuario sus,seg_datos_personales sdp where hf.codigo=afc.codigo_for_fk and ac.col_codigo=afc.codigo_cola_fk and ac.numero_documento =afc.cedulaPac and ac.numero_documento='"+NumDocumento+"' and ac.tipo_documento='"+TipoDoc+"' and afc.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk and afc.estado=1");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormatosPreingreso "+ex);
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
        	rs=st.executeQuery("select codigo,nombre_tiporespuesta,tipo from hic_tipo_respuesta where nombre_tiporespuesta='"+pregunta+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerCodigoPregunta "+ex);
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
        	rs=st.executeQuery("select codigo,nombre_respuesta from hic_respuestas where nombre_respuesta='"+respuesta+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerCodigoRespuesta "+ex);
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
			    ps=con.conn.prepareStatement("insert into hic_tipo_respuesta(nombre_tiporespuesta,tipo)values(?,?)");
			    ps.setString(1, cf.getNombrePregunta());
			    ps.setString(2, cf.getTipoPregunta());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ CrearPregunta "+ex);
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
			    ps=con.conn.prepareStatement("insert into hic_respuestas(nombre_respuesta)values(?)");
			    ps.setString(1, cf.getNombreRespuesta());
			   	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ CrearRespuesta "+ex);
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
			    ps=con.conn.prepareStatement("insert into hic_condicion_respuesta(codigo_tiporespuesta_fk,codigo_respuesta_fk)values(?,?)");
			    ps.setString(1,pr.getcodigo_item_formato_fk());
			    ps.setString(2, pr.getcodigo_respuesta_fk());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ RelacionPreguntaRespuesta "+ex);
			}
		}
	
}
