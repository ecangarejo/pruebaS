package soporte_metodo;
import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodosSolicitudes {
	
	public java.sql.ResultSet CodigoEstado(){	
		/**
		 * Busca el codigo del estado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, codigo from sop_estado");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>CodigoEstado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TipoEstado(){	
		/**
		 * Busca el codigo del estado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sopEs.codigo,sopEs.tipo_estado FROM sop_estado sopEs");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>TipoEstado "+ex);
        }	
        return rs;
    }
	
	

	public java.sql.ResultSet NombreSubarea(int CodSolicitud){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select Su.nombre from sop_solicitud Sso, sop_subarea Su where Sso.cod_SubArea_fk= Su.codigo and Sso.codigo="+CodSolicitud);
			
		}catch(Exception ex){
		 	System.out.println("Error en MetodosSolicitudes>>NombreSubarea"+ex);
		}
		
		return rs;
	}
	
public java.sql.ResultSet NombresProblemas(int CodDetSolicitud){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		try{
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT Pr.descripcion FROM sop_detalle_solicitud Sdts, sop_detalle_problema Sdtp, sop_problemas Pr WHERE Sdts.codigo=Sdtp.cod_det_solicitud_fk AND Sdtp.cod_problema_fk=Pr.codigo AND Sdts.codigo="+CodDetSolicitud);
			
		}catch(Exception ex){
		 	System.out.println("Error en MetodosSolicitudes>>NombresProblemas"+ex);
		}
		
		return rs;
	}
	
public java.sql.ResultSet EstadoTecnico(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM sop_estado_tecnico");
		
	}catch(Exception ex){
		
		System.out.println("Error en MetodosSolicitudes>>EstadoTecnico"+ex);
	}
	
	
	return rs;
}



public java.sql.ResultSet DetallesSolicitud(int CodSolicitud){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT Dso.horaenviado, Dso.fechaenviado FROM sop_detalle_solicitud Dso, sop_solicitud Sso WHERE Dso.cod_solicitud_fk=Sso.codigo AND Sso.codigo"+CodSolicitud);
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>DetallesSolicitud"+ex);
		
	}
	
	return rs;
}

public java.sql.ResultSet Prioridad(int CodSolicitud){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT Pri.descripcion FROM sop_prioridad Pri, sop_Solicitud Sso WHERE Sso.cod_prioridad_fk=Pri.codigo AND Sso.codigo="+CodSolicitud);
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>Prioridad"+ex);
		
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitud(String fechahoy){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE NOT sopEst.codigo=5 " +
						     "AND sopDs.tiposolicitud='Hardware' " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "ORDER BY sopSo.fechaSolicitud DESC, sopSo.horaSolicitud DESC");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitud"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitudReq(String fechahoy){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE NOT sopEst.codigo=5 " +
						     "AND sopDs.tiposolicitud='Requerimiento' " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "ORDER BY sopSo.fechaSolicitud DESC, sopSo.horaSolicitud DESC");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitud"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerFechaObservacion(String CdSol){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT DISTINCT sopRe.horarevision FROM sop_records sopRe, sop_solicitud sopSo WHERE sopSo.codigo=sopRe.cod_solicitud_fk AND sopRe.cod_solicitud_fk='"+CdSol+"' ");
		
		
	}catch(Exception ex){
		System.out.println("ERROR en MetodosSolicitudes>>ObtenerFechaSolicitudes"+ex);
		
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitudesFinalizadas(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado,sopDs.fechafinalizado,sopDs.horafinalizado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE sopEst.codigo=5 " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "ORDER BY sopDs.fechafinalizado DESC, sopDs.horafinalizado DESC limit 100");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitud"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitudesFinalizadasReq(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado,sopDs.fechafinalizado,sopDs.horafinalizado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE sopEst.codigo=5 " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "AND sopDs.tiposolicitud='Requerimiento' " +
						     "ORDER BY sopDs.fechafinalizado DESC, sopDs.horafinalizado DESC limit 100");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitudesFinalizadasReq"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitudesPorFechas(String FechaIni, String FechaFin){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado,sopDs.fechafinalizado,sopDs.horafinalizado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE sopEst.codigo=5 " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "AND sopDS.tiposolicitud='Hardware' " +
						     "AND sopSo.fechaSolicitud BETWEEN '"+FechaIni+"' AND '"+FechaFin+"' " +
						     "ORDER BY sopSo.fechaSolicitud DESC, sopSo.horaSolicitud DESC");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitud"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitudesPorFechasReq(String FechaIni, String FechaFin){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado,sopDs.fechafinalizado,sopDs.horafinalizado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE sopEst.codigo=5 " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "AND sopSo.fechaSolicitud BETWEEN '"+FechaIni+"' AND '"+FechaFin+"' " +
						     "AND sopDs.tiposolicitud='Requerimiento' " +
						     "ORDER BY sopSo.fechaSolicitud DESC, sopSo.horaSolicitud DESC");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitudPorFechasReq"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerSolicitudesPorArea(String CodAreaBus){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado,sopDs.fechafinalizado,sopDs.horafinalizado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE sopEst.codigo=5 " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "AND sopDs.tiposolicitud='Hardware' " +
						     "AND sopSo.cod_SubArea_fk='"+CodAreaBus+"' " +
						     "ORDER BY sopSo.fechaSolicitud DESC, sopSo.horaSolicitud DESC");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitudPorArea"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerSolicitudesPorAreaReq(String CodAreaBus){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario, sopSA.nombre,sopSo.fechaSolicitud,sopSo.horaSolicitud, sopPrio.descripcion,sopSo.codigo,sopEst.tipo_estado,sopDs.fechafinalizado,sopDs.horafinalizado  " +
						     "FROM sop_solicitud sopSo, sop_subarea sopSA, seg_usuario su, seg_datos_personales sdt, sop_detalle_solicitud sopDS, sop_prioridad sopPrio,sop_estado sopEst  " +
						     "WHERE sopEst.codigo=5 " +
						     "AND sopSo.cod_Usuario_fk=su.usu_codigo " +
						     "AND su.dat_codigo_fk=sdt.dat_codigo " +
						     "AND sopSo.cod_SubArea_fk=sopSA.codigo " +
						     "AND sopSo.codigo=sopDS.cod_solicitud_fk " +
						     "AND sopDS.cod_prioridad_fk=sopPrio.codigo " +
						     "AND sopEst.codigo=sopDS.cod_est_fk " +
						     "AND sopSo.cod_SubArea_fk='"+CodAreaBus+"' " +
						     "AND sopDs.tiposolicitud='Requerimiento' " +
						     "ORDER BY sopSo.fechaSolicitud DESC, sopSo.horaSolicitud DESC");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitudPorAreaReq"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerEstadoProblemas(String codigosolicitud){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sopDP.cod_est_problema_fk FROM sop_detalle_problema sopDP WHERE sopDP.cod_solicitud_fk='"+codigosolicitud+"'");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerEstadoProblemas"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet TipoEstadoFinalizado(){	
	/**
	 * Busca el codigo del estado
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT sopEs.codigo FROM sop_estado sopEs where sopEs.codigo=5");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoEspecialidades>>TipoEstado "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerObservacionDetalle(String codsol){
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sopDs.observacion FROM sop_detalle_solicitud sopDs WHERE sopDs.cod_solicitud_fk='"+codsol+"'");
		
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerObservacionDetalle"+ex);
		
	}
	
	
	return rs;
}


public java.sql.ResultSet ObtenerEstadoAlex(){
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT se.cod_est_tec_fk,st.estado FROM sop_estadistica se, sop_estado_tecnico st WHERE st.codigo=se.cod_est_tec_fk AND se.codigo=(SELECT MAX(se.codigo) FROM sop_estadistica se)");
		
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerEstadoAlex"+ex);
		
	}
	
	
	return rs;
}

public java.sql.ResultSet ReporteProblema(String fecharep, String horarep){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sp.descripcion,COUNT(sdp.cod_problema_fk) AS solicitud " +
				"FROM sop_detalle_problema sdp, sop_solicitud ss, sop_problemas sp " +
				"WHERE ss.codigo=sdp.cod_solicitud_fk " +
				"AND sdp.cod_problema_fk=sp.codigo " +
				"AND ss.fechaSolicitud BETWEEN '"+fecharep+"' AND '"+horarep+"' " +
				"GROUP BY sdp.cod_problema_fk " +
				"ORDER BY solicitud DESC LIMIT 1");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ReporteProblema"+ex);
		
	}
	
	return rs;
}
public java.sql.ResultSet ReporteProblemaReq(String fecharep, String horarep){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT spr.descripcion,COUNT(sdp.cod_pro_req_fk) AS solicitud " +
				"FROM sop_detalle_problema sdp, sop_solicitud ss, sop_problemas_requerimientos spr " +
				"WHERE ss.codigo=sdp.cod_solicitud_fk " +
				"AND sdp.cod_pro_req_fk=spr.codigo " +
				"AND ss.fechaSolicitud BETWEEN '"+fecharep+"' AND '"+horarep+"' " +
				"GROUP BY sdp.cod_pro_req_fk " +
				"ORDER BY solicitud DESC LIMIT 1");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ReporteProblemaReq"+ex);
		
	}
	
	return rs;
}
public java.sql.ResultSet TotalProblemas(String fecharep2, String horarep2){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT COUNT(sdp.cod_problema_fk) AS problemas FROM sop_detalle_problema sdp, sop_solicitud ss " +
				"WHERE ss.fechaSolicitud BETWEEN '"+fecharep2+"' AND '"+horarep2+"' " +
				"AND ss.codigo=sdp.cod_solicitud_fk");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>TotalProblemas"+ex);
		
	}
	
	return rs;
}
public java.sql.ResultSet TotalProblemasReq(String fecharep2, String horarep2){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT COUNT(sdp.cod_pro_req_fk) AS problemas " +
				"FROM sop_detalle_problema sdp, sop_solicitud ss " +
				"WHERE ss.fechaSolicitud BETWEEN '"+fecharep2+"' AND '"+horarep2+"' " +
				"AND ss.codigo=sdp.cod_solicitud_fk");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>TotalProblemasReq"+ex);
		
	}
	
	return rs;
}

public java.sql.ResultSet TotalSolicitudes(String fecharep2, String horarep2){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT COUNT(ss.codigo) AS Solicitudes FROM sop_solicitud ss,sop_detalle_solicitud sdp "+
                             "WHERE ss.fechaSolicitud BETWEEN '"+fecharep2+"' AND '"+horarep2+"' " +
                             "AND ss.codigo=sdp.cod_solicitud_fk " +
                             "AND sdp.tiposolicitud='Hardware'"); 
				
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>TotalSolicitudes"+ex);
		
	}
	
	return rs;
}
public java.sql.ResultSet TotalSolicitudesReq(String fecharep2, String horarep2){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT COUNT(ss.codigo) AS Solicitudes FROM sop_solicitud ss,sop_detalle_solicitud sdp "+
                             "WHERE ss.fechaSolicitud BETWEEN '"+fecharep2+"' AND '"+horarep2+"' " +
                             "AND ss.codigo=sdp.cod_solicitud_fk " +
                             "AND sdp.tiposolicitud='Requerimiento'"); 
				
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>TotalSolicitudesReq"+ex);
		
	}
	
	return rs;
}
public java.sql.ResultSet CodProblemaComun (String fecharepC, String horarepC){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sp.codigo,COUNT(sdp.cod_problema_fk) AS solicitud " +
				"FROM sop_detalle_problema sdp, sop_solicitud ss, sop_problemas sp " +
				"WHERE ss.codigo=sdp.cod_solicitud_fk " +
				"AND sdp.cod_problema_fk=sp.codigo " +
				"AND ss.fechaSolicitud BETWEEN '"+fecharepC+"' AND '"+horarepC+"' " +
				"GROUP BY sdp.cod_problema_fk " +
				"ORDER BY solicitud DESC LIMIT 1");
		
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>CodProblemaComun"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet CodProblemaComunReq(String fecharepC, String horarepC){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sp.codigo,COUNT(sdp.cod_pro_req_fk) AS solicitud " +
				"FROM sop_detalle_problema sdp, sop_solicitud ss, sop_problemas_requerimientos sp " +
				"WHERE ss.codigo=sdp.cod_solicitud_fk " +
				"AND sdp.cod_pro_req_fk=sp.codigo " +
				"AND ss.fechaSolicitud BETWEEN '"+fecharepC+"' AND '"+horarepC+"' " +
				"GROUP BY sdp.cod_pro_req_fk " +
				"ORDER BY solicitud DESC LIMIT 1");
		
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>CodProblemaComunReq"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerSubAreaComun (String CodProCom,String FechaIniss,String FechaFinss){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sba.nombre,COUNT(sdp.cod_problema_fk) AS problema " +
				"FROM sop_subarea sba, sop_problemas sp, sop_detalle_problema sdp, sop_solicitud ss " +
				"WHERE sdp.cod_problema_fk='"+CodProCom+"' " +
				"AND sdp.cod_problema_fk=sp.codigo " +
				"AND ss.cod_SubArea_fk=sba.codigo " +
				"AND ss.codigo=sdp.cod_solicitud_fk " +
				"AND ss.fechaSolicitud BETWEEN '"+FechaIniss+"' AND '"+FechaFinss+"' " +
				"GROUP BY sba.nombre " +
				"ORDER BY problema DESC LIMIT 1");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSubAreaComun"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerSubAreaComunReq(String CodProCom,String FechaIniss,String FechaFinss){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sba.nombre,COUNT(sdp.cod_pro_req_fk) AS problema " +
				"FROM sop_subarea sba, sop_problemas_requerimientos sp, sop_detalle_problema sdp, sop_solicitud ss " +
				"WHERE sdp.cod_pro_req_fk='"+CodProCom+"' " +
				"AND sdp.cod_pro_req_fk=sp.codigo " +
				"AND ss.cod_SubArea_fk=sba.codigo " +
				"AND ss.codigo=sdp.cod_solicitud_fk " +
				"AND ss.fechaSolicitud BETWEEN '"+FechaIniss+"' AND '"+FechaFinss+"' " +
				"GROUP BY sba.nombre " +
				"ORDER BY problema DESC LIMIT 1");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSubAreaComunReq"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerSubAreaRep (String fechainisub, String fechafinsub){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sba.nombre,COUNT(ss.cod_SubArea_fk) AS problemas " +
				"FROM  sop_solicitud ss, sop_subarea sba, sop_detalle_solicitud sds " +
                "WHERE ss.cod_SubArea_fk=sba.codigo " +
                "AND ss.codigo=sds.cod_solicitud_fk "+
                "AND sds.tiposolicitud='Hardware' "+
				"AND ss.fechaSolicitud BETWEEN '"+fechainisub+"' AND '"+fechafinsub+"' " +
				"GROUP BY sba.nombre " +
				"ORDER BY problemas DESC LIMIT 1");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSubAreaRep"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerSubAreaRepReq(String fechainisub, String fechafinsub){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sba.nombre,COUNT(ss.cod_SubArea_fk) AS problemas " +
				"FROM  sop_solicitud ss, sop_subarea sba, sop_detalle_solicitud sds " +
				"WHERE ss.cod_SubArea_fk=sba.codigo " +
				"AND ss.fechaSolicitud BETWEEN '"+fechainisub+"' AND '"+fechafinsub+"' " +
				"AND ss.codigo=sds.cod_solicitud_fk " +
				"AND sds.tiposolicitud='Requerimiento' " +
				"GROUP BY sba.nombre " +
				"ORDER BY problemas DESC LIMIT 1");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSubAreaRepReq"+ex);
	}
	
	return rs;
}
public java.sql.ResultSet ObtenerEstadoProblemasUsuario(String codsol){
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new 	Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT soP.descripcion,sopEst.tipo_estado " +
				"FROM sop_detalle_solicitud soDS, sop_problemas soP,sop_solicitud soSo,sop_detalle_problema soDP,sop_estado sopEst " +
				"WHERE soSo.codigo='"+codsol+"' " +
				"AND soSo.codigo=soDS.cod_solicitud_fk " +
				"AND soSo.codigo=soDP.cod_solicitud_fk " +
				"AND sopEst.codigo=soDP.cod_est_problema_fk "+
				"AND soDP.cod_problema_fk=soP.codigo");
		
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerEstadoProblemasUsuario"+ex);
		
	}
	
	
	return rs;
}

public java.sql.ResultSet ValidarSolicitud(String CodUsuario, String CodSubArea, String CodProblema){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sdp.cod_problema_fk FROM sop_detalle_problema sdp,sop_detalle_solicitud sds,sop_solicitud ss " +
				             "WHERE ss.cod_Usuario_fk='"+CodUsuario+"' " +
				             "AND ss.cod_SubArea_fk='"+CodSubArea+"' " +
				             "AND ss.codigo=sds.cod_solicitud_fk " +
				             "AND NOT sdp.cod_est_problema_fk='5' " +
				             "AND NOT sdp.cod_est_problema_fk='6'" +
				             "AND ss.codigo=sdp.cod_solicitud_fk " +
				             "AND sdp.cod_problema_fk='"+CodProblema+"'");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ValidarSolicitudes"+ex);
		
	}
	
	
	return rs;
}




public java.sql.ResultSet ObtenerCodSolicitudUsuario(String CodUsuario){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT sopSo.codigo " +
				"FROM sop_solicitud sopSo, sop_detalle_solicitud sopDs, seg_usuario Us " +
				"WHERE  Us.usu_codigo='"+CodUsuario+"' " +
			    "AND sopDs.cod_est_fk='1'" +
				"AND Us.usu_codigo=sopSo.cod_Usuario_fk " +
				"AND sopSo.codigo=sopDs.cod_solicitud_fk");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerCodSolicitudUsuario"+ex);
		
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerCodSolicitudUsuarioMostrar(String CodUsuario, String Fechahoy){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT DISTINCT sopSo.codigo " +
				"FROM  sop_solicitud sopSo, sop_detalle_solicitud sopDs, seg_usuario Us " +
				"WHERE  Us.usu_codigo='"+CodUsuario+"' " +
				"AND (sopSo.fechaSolicitud='"+Fechahoy+"' " +
			    "OR NOT sopDs.cod_est_fk='5') " +
				"AND Us.usu_codigo=sopSo.cod_Usuario_fk " +
				"AND sopSo.codigo=sopDs.cod_solicitud_fk " +
		        "AND sopDs.tiposolicitud='Hardware'");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerCodSolicitudUsuario"+ex);
		
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerCodSolicitudUsuarioMostrarReq(String CodUsuario, String Fechahoy){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT DISTINCT sopSo.codigo " +
				"FROM  sop_solicitud sopSo, sop_detalle_solicitud sopDs, seg_usuario Us " +
				"WHERE  Us.usu_codigo='"+CodUsuario+"' " +
				"AND (sopSo.fechaSolicitud='"+Fechahoy+"' " +
			    "OR NOT sopDs.cod_est_fk='5') " +
				"AND Us.usu_codigo=sopSo.cod_Usuario_fk " +
				"AND sopSo.codigo=sopDs.cod_solicitud_fk " +
				"AND sopDs.tiposolicitud='Requerimiento'");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerCodSolicitudUsuarioReq"+ex);
		
	}
	
	return rs;
}


public java.sql.ResultSet ObtenerDatosObservacion(String CdSoli){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion ();
		st = con.conn.createStatement();
		rs = st.executeQuery("select distinct sopRe.horarevision, sopRe.observacion from sop_records sopRe, sop_solicitud sopSo " +
				"where sopRe.cod_solicitud_fk='"+CdSoli+"' " +
				"and sopSo.codigo=sopRe.cod_solicitud_fk");

	}catch(Exception ex){
		System.out.println("ERROR en MetodosSolicitdes>>ObtenerDatosObservacion"+ex);
		
	}
	
	
	return rs;
}


public java.sql.ResultSet ObtenerProblemas(String codsol){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT soDP.observacion,soP.descripcion,soP.codigo,soDP.cod_est_problema_fk,sopEst.tipo_estado " +
				"FROM sop_detalle_solicitud soDS, sop_problemas soP,sop_solicitud soSo,sop_detalle_problema soDP,sop_estado sopEst " +
				"WHERE soSo.codigo='"+codsol+"' " +
				"AND soSo.codigo=soDS.cod_solicitud_fk " +
				"AND soSo.codigo=soDP.cod_solicitud_fk " +
				"AND sopEst.codigo=soDP.cod_est_problema_fk "+
				"AND soDP.cod_problema_fk=soP.codigo ");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitud"+ex);
	}
	
	return rs;
}

public java.sql.ResultSet ObtenerProblemasReq(String codsol){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT soDP.observacion,soP.descripcion,soP.codigo,soDP.cod_est_problema_fk,sopEst.tipo_estado " +
				"FROM sop_detalle_solicitud soDS, sop_problemas_requerimientos soP,sop_solicitud soSo,sop_detalle_problema soDP,sop_estado sopEst " +
				"WHERE soSo.codigo='"+codsol+"' " +
				"AND soSo.codigo=soDS.cod_solicitud_fk " +
				"AND soSo.codigo=soDP.cod_solicitud_fk " +
				"AND sopEst.codigo=soDP.cod_est_problema_fk  "+
				"AND soDP.cod_pro_req_fk=soP.codigo ");
		
	}catch(Exception ex){
		System.out.println("Error en MetodosSolicitudes>>ObtenerSolicitud"+ex);
	}
	
	return rs;
}


public void IngresarEstadistica(String CodEstadoA,String fechaestado,String horaestado){
	
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_estadistica(cod_est_tec_fk,fechaestado,horaestado)values(?,?,?)");
		ps.setString(1, CodEstadoA);
		ps.setString(2, fechaestado);
		ps.setString(3, horaestado);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosSolicitudes>>IngresarEstadistica "+ex);
		
	}
	
	
}





public void IngresarRecord(  String CodSolicitud,String CodProble,String CodEstado, String FechRev, String HoraRev,String Observaciones){
	
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_records(cod_solicitud_fk,cod_problema_fk,cod_estado_fk,fecharevision,horarevision,observacion)values(?,?,?,?,?,?)");
		ps.setString(1, CodSolicitud);
		ps.setString(2, CodProble);
		ps.setString(3, CodEstado);
		ps.setString(4, FechRev);
		ps.setString(5, HoraRev);
		ps.setString(6, Observaciones);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosSolicitudes>>IngresarRecord "+ex);
		
	}
	
	
}
public void IngresarReporte(String FechaActual,String FechaInicial,String FechaFinal){
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_reporte(fecha_actual,fechaini,fechafin)values(?,?,?)");
		ps.setString(1, FechaActual);
		ps.setString(2, FechaInicial);
		ps.setString(3, FechaFinal);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosSolicitudes>>IngresarReporte "+ex);
		
	}
	
}
public void IngresarRecordReq(  String CodSolicitud,String CodProble,String CodEstado, String FechRev, String HoraRev,String Observaciones){
	
	try{
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("insert into sop_records(cod_solicitud_fk,cod_pro_req_fk,cod_estado_fk,fecharevision,horarevision,observacion)values(?,?,?,?,?,?)");
		ps.setString(1, CodSolicitud);
		ps.setString(2, CodProble);
		ps.setString(3, CodEstado);
		ps.setString(4, FechRev);
		ps.setString(5, HoraRev);
		ps.setString(6, Observaciones);
		ps.executeUpdate();
		ps.close();
		con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosSolicitudes>>IngresarRecordReq "+ex);
		
	}
	
	
}
public void ActualizarDetalleSolicitud(String CodEstado,String FechaFinalizado,String HoraFinalizado, String CodSolicitud){
	
	PreparedStatement st = null;
	try{
		Conexion con=new Conexion();
		st= con.conn.prepareStatement("UPDATE sop_detalle_solicitud SET cod_est_fk='"+CodEstado+"',fechafinalizado='"+FechaFinalizado+"',horafinalizado='"+HoraFinalizado+"' WHERE cod_solicitud_fk='"+CodSolicitud+"'	");
		st.executeUpdate();	 
     	st.close();
     	con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosSolicitudes>>ActualizarDetalleSolicitud "+ex);
	}
	
}
public void ActualizarEstado(String CodEstado,String CodSoli, String CodProblema){
    /**
     * actualiza todos los datos del medico seleccionado.
     */
	
	PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE sop_detalle_problema set cod_est_problema_fk='"+CodEstado+"' where cod_solicitud_fk='"+CodSoli+"' and cod_problema_fk='"+CodProblema+"'");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodosSolicitudes>>ActualizarEstado "+ex);
     	ex.getMessage();	     
     }	
 }

public void ActualizarEstadoReq(String CodEstado,String CodSoli, String CodProblema){
    /**
     * actualiza todos los datos del medico seleccionado.
     */
	
	PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE sop_detalle_problema set cod_est_problema_fk='"+CodEstado+"' where cod_solicitud_fk='"+CodSoli+"' and cod_pro_req_fk='"+CodProblema+"'");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodosSolicitudes>>ActualizarEstadoReq "+ex);
     	ex.getMessage();	     
     }	
 }
public void ActualizarDetalleObservacion(String Obser, String CodSolicit){
	
	PreparedStatement st = null;
	try{
		Conexion con = new Conexion();
		st = con.conn.prepareStatement("UPDATE sop_detalle_solicitud SET observacion='"+Obser+"' WHERE cod_solicitud_fk='"+CodSolicit+"'");
		st.executeUpdate();	 
     	st.close();
     	con.cerrar();
	}catch(Exception ex){
		System.out.println("ERROR EN MetodosSolicitudes>>ActualizarDetalleObservacion "+ex);
		
	}
	
}



}
